package com.resonai.mall

import app.cash.turbine.testIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class MallTest {

    //region TWO INPUTS
    @Test
    fun input2TriggersAndReadsTest() = runTest {
        val numTriggers = 5
        val numReads = 6
        val numRepeats = 7
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val in2 = Input2(
            flow1 inas trigger, flow2 inas reads
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = in2.gather().mallIn(this)
            val turbine = flow3.testIn(this)
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals(
                        Pair("$i", numReads - 1),
                        (turbine.awaitItem() as Pair<*, *>)
                    )
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numReads) { j ->
                repeat(numRepeats) {
                    flow2.emit(j)
                }
            }
            repeat(numRepeats) {
                flow1.emit("$i")
            }
        }

        assertEquals(numReceived, numTriggers * numRepeats)
        job.cancel()
    }

    @Test
    fun twoTriggersTest() = runTest {
        val numTriggers = 10
        val numRepeats = 3
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        var numReceived = 0
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = flow1.combine(flow2) { a, b ->
                Pair(a, b)
            }.mallIn(this)
            val turbine = flow3.testIn(this)
            assertEquals(Pair("-1", -1), (turbine.awaitItem() as Pair<*, *>))
            numReceived++
            repeat(numTriggers) { i ->
                assertEquals(Pair("$i", i - 1), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
                repeat(2 * numRepeats - 1) {
                    assertEquals(Pair("$i", i), (turbine.awaitItem() as Pair<*, *>))
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow1.emit("$i")
                flow2.emit(i)
            }
        }

        assertEquals(numReceived, 2 * numTriggers * numRepeats + 1)
        job.cancel()
    }

    @Test
    fun input2TriggersTest() = runTest {
        val numTriggers = 10
        val numRepeats = 4
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val in2 = Input2(flow1 inas trigger, flow2 inas trigger)
        var numReceived = 0
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = in2.gather().mallIn(this)
            val turbine = flow3.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(Pair("$i", i - 1), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
                repeat(2 * numRepeats - 1) {
                    assertEquals(Pair("$i", i), (turbine.awaitItem() as Pair<*, *>))
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow1.emit("$i")
                flow2.emit(i)
            }
        }

        assertEquals(numReceived, 2 * numTriggers * numRepeats)
        job.cancel()
    }

    @Test
    fun twoZipsTest() = runTest {
        val numTriggers = 20
        val numRepeats = 5
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode(-1)
        val flow3 = MallNode(-3)
        CoroutineScope(Dispatchers.Unconfined).op(flow1.zip, flow2.zip, { a, b ->
            a + b
        }, flow3)
        val job = launch(Dispatchers.Unconfined) {
            val turbine = flow3.testIn(this)
            // flow3 is already hot, even without emitting, so we need to pop its value here
            assertEquals(-3, turbine.awaitItem())
            numReceived++
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals(2 * i, turbine.awaitItem())
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }
        val jobs = listOf(
            // launch the input separately to model two independent producers
            launch(Dispatchers.Unconfined) {
                repeat(numTriggers) { i ->
                    repeat(numRepeats) {
                        flow1.emit(i)
                    }
                }
            }, launch(Dispatchers.Unconfined) {
                repeat(numTriggers) { i ->
                    repeat(numRepeats) {
                        flow2.emit(i)
                    }
                }
            })
        jobs.forEach { it.join() }
        assertEquals(numReceived, numTriggers * numRepeats + 1)
        jobs.forEach { it.cancel() }
        job.cancel()
    }

    @Test
    fun input2ZipsTest() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode(-1)
        val in2 = Input2(zip(flow1), zip(flow2))
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = in2.gather().map {
                it.first + it.second
            }.stateIn(this)
            val turbine = flow3.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(2 * i, turbine.awaitItem())
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            if (i % 2 == 0) {
                flow1.emit(i)
                flow1.emit(i + 1)
            } else {
                flow2.emit(i - 1)
                flow2.emit(i)
            }
        }

        assertEquals(numReceived, numTriggers)
        job.cancel()
    }

    @Test
    fun inputsTupleTest() = runTest {
        var numReceived = 0
        var numZipped = 0
        val numTriggers = 10
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)

        val job = launch(Dispatchers.Unconfined) {
            val flow12 = combineTupleGen(flow1, flow2).stateIn(this)
            val turbine = flow12.testIn(this)
            val zip12 = zipTupleGen(flow1, flow2).stateIn(this)
            val zipper = zip12.testIn(this)
            assertEquals(Pair("-1", -1), (turbine.awaitItem() as Pair<*, *>))
            numReceived++
            assertEquals(Pair("-1", -1), (zipper.awaitItem() as Pair<*, *>))
            numZipped++

            repeat(numTriggers) { i ->
                assertEquals(Pair("$i", i - 1), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
                assertEquals(Pair("$i", i), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
                assertEquals(Pair("$i", i), (zipper.awaitItem() as Pair<*, *>))
                numZipped++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
            assertEquals(listOf(), zipper.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow1.emit("$i")
            flow2.emit(i)
        }

        assertEquals(numReceived, 2 * numTriggers + 1)
        assertEquals(numZipped, numTriggers + 1)
        job.cancel()
    }
    //endregion

    //region THREE INPUTS
    @Test
    fun input3TriggersTest() = runTest {
        val numTriggers = 20
        val numRepeats = 5
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val flow3 = MallNode("`")
        val in3 = Input3(
            flow1 inas trigger,
            flow2 inas trigger,
            flow3 inas trigger
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow123 = in3.gather().mallIn(this)
            val turbine = flow123.testIn(this)
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals(
                        Triple("$i", -1, "`"),
                        (turbine.awaitItem() as Triple<*, *, *>)
                    )
                    numReceived++
                }
            }
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals(
                        Triple("${numTriggers - 1}", i, "`"),
                        (turbine.awaitItem() as Triple<*, *, *>)
                    )
                    numReceived++
                }
            }
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals(
                        Triple(
                            "${numTriggers - 1}",
                            numTriggers - 1,
                            ('a' + i).toString()
                        ), (turbine.awaitItem() as Triple<*, *, *>)
                    )
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow1.emit("$i")
            }
        }
        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow2.emit(i)
            }
        }
        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow3.emit(('a' + i).toString())
            }
        }

        assertEquals(numReceived, 3 * numTriggers * numRepeats)
        job.cancel()
    }

    @Test
    fun input3Triggers2AndReadsTest() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val flow3 = MallNode("`")
        val in3 = Input3(
            flow1.trigger,
            flow2.trigger,
            flow3.reads
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow123 = in3.gather().stateIn(this)
            val turbine = flow123.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(
                    Triple("$i", i - 1, ('a' + 2 * i - 1).toString()),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++

                assertEquals(
                    Triple("$i", i, ('a' + 2 * i).toString()),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow1.emit("$i")
            flow3.emit(('a' + 2 * i).toString())
            flow2.emit(i)
            flow3.emit(('a' + 2 * i + 1).toString())
        }

        assertEquals(numReceived, 2 * numTriggers)
        job.cancel()
    }

    @Test
    fun input3TriggersAndReads2Test() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val flow3 = MallNode("`")
        val in3 = Input3(
            flow1.trigger,
            flow2.reads,
            flow3.reads
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow123 = in3.gather().stateIn(this)
            val turbine = flow123.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(
                    Triple("$i", 3 * i - 1, ('a' + 2 * i - 1).toString()),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow1.emit("$i")
            flow2.emit(3 * i)
            flow3.emit(('a' + 2 * i + 1).toString())
            flow2.emit(3 * i + 1)
            flow2.emit(3 * i + 2)
        }

        assertEquals(numReceived, numTriggers)
        job.cancel()
    }

    @Test
    fun input3TriggersAndZips2Test() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-3)
        val flow3 = MallNode("`")
        val in3 = Input3(
            flow1.trigger,
            flow2.zip,
            flow3.zip
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow123 = in3.gather().stateIn(this)
            val turbine = flow123.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(
                    Triple("$i", 3 * (i - 1), ('a' + i - 1).toString()),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++

                assertEquals(
                    Triple("$i", 3 * i, ('a' + i).toString()),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow2.emit(3 * i)
            flow1.emit("$i")
            flow3.emit(('a' + i).toString())
        }

        assertEquals(numReceived, 2 * numTriggers)
        job.cancel()
    }

    @Test
    fun input3Zips2ReadsTest() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode("`")
        val flow2 = MallNode(-3)
        val flow3 = MallNode("-1")
        val in3 = Input3(
            zip(flow1),
            zip(flow2),
            reads(flow3)
        )
        val job = launch(Dispatchers.Unconfined) {
            val flow123 = in3.gather().stateIn(this)
            val turbine = flow123.testIn(this)
            repeat(numTriggers) { i ->
                assertEquals(
                    Triple(('a' + i).toString(), 3 * i, "$i"),
                    (turbine.awaitItem() as Triple<*, *, *>)
                )
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow2.emit(3 * i)
            flow3.emit("$i")
            flow1.emit(('a' + i).toString())
        }

        assertEquals(numReceived, numTriggers)
        job.cancel()
    }
    //endregion

    //region FOUR INPUTS
    @Test
    fun input4TriggersZipsReadsTest() = run {
        repeat(4) { readsIn ->
            val triggersAndZipsIn = 4 - readsIn
            for (zipsIn in 0..triggersAndZipsIn) {
                if (zipsIn == 1) {
                    continue
                }
                val triggersIn = triggersAndZipsIn - zipsIn
                println("Running test with $readsIn reads, $triggersIn triggers, $zipsIn zips")
                runTest {
                    val numTriggers = 100
                    var numReceived = 0
                    val flows = listOf(
                        MallNode(-3), MallNode(-2),
                        MallNode(-1), MallNode(0)
                    )
                    val inFlows = flows.mapIndexed { i, it ->
                        InFlow(
                            it,
                            when {
                                (i < triggersIn) -> FlowsInLike.TRIGGER
                                (i < triggersAndZipsIn) -> FlowsInLike.ZIP
                                else -> FlowsInLike.READS
                            }
                        )
                    }
                    val in4 = Input4(inFlows[0], inFlows[1], inFlows[2], inFlows[3])
                    val job = launch(Dispatchers.Unconfined) {
                        val flow4 = in4.gather().stateIn(this)
                        val turbine = flow4.testIn(this)
                        repeat(numTriggers) { i ->
                            repeat(triggersIn + if (zipsIn > 0) 1 else 0) { j ->
                                val res = (turbine.awaitItem() as Quadruple<*, *, *, *>)
                                var expect = Quadruple(
                                    4 * i + 1,
                                    4 * i + if (j in 1 until triggersIn) 2 else -2,
                                    4 * i + if (j in 2 until triggersIn) 3 else -1,
                                    4 * i + if (j in 3 until triggersIn) 4 else 0
                                )

                                if (zipsIn > 0 && j == triggersIn) {
                                    // This can be incorporated into the inline ifs above, but it would make it really ugly!
                                    expect = Quadruple(
                                        4 * i + 1,
                                        4 * i + if (1 < triggersAndZipsIn) 2 else -2,
                                        4 * i + if (2 < triggersAndZipsIn) 3 else -1,
                                        4 * i + if (3 < triggersAndZipsIn) 4 else 0
                                    )
                                }

                                assertEquals(
                                    expect,
                                    res
                                )
                                numReceived++
                            }
                        }

                        assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
                    }

                    repeat(numTriggers) { i ->
                        repeat(4) { j ->
                            val x = 4 * i + j + 1
                            flows[j].emit(x)
                        }
                    }

                    assertEquals(numReceived, (triggersIn + if (zipsIn > 0) 1 else 0) * numTriggers)
                    job.cancel()
                }
            }
        }
    }
    //endregion

    //region COMBINATIONS/GRAPHS
    @Test
    fun simpleOpTransformTest() = runTest {
        val numTriggers = 10
        val numRepeats = 4
        val flow1 = MallNode(-1)
        val flow2 = MallNode("-999")
        flow1.transform(CoroutineScope(context = Dispatchers.Unconfined), flow2, Int::toString)
        var numReceived = 0
        val job = launch(Dispatchers.Unconfined) {
            val turbine = flow2.testIn(this)
            // read off first value of flow2, which is already hot and has gotten the first transform
            assertEquals("-1", turbine.awaitItem())
            numReceived++
            repeat(numTriggers) { i ->
                repeat(numRepeats) {
                    assertEquals("$i", turbine.awaitItem())
                    numReceived++
                }
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numRepeats) {
                flow1.emit(i)
            }
        }

        job.join()   // Wait for the job to finish
        assertEquals(numReceived, numRepeats * numTriggers + 1)
        job.cancel()
    }

    @Test
    fun opGraphTransformTest() = runTest {
        val UNCONFINED = CoroutineScope(context = Dispatchers.Unconfined)
        val numTriggers = 10
        val flow1 = MallNode(-1)
        val flow2 = MallNode(-1)
        val flow3 = MallNode("-999")
        val flow5 = MallNode("1")
        val flow4 = MallNode("abb")

        // Operator definitions
        flow1.transform(UNCONFINED, flow5) { (-it).toString() }
        Input2(
            flow1.trigger,
            flow2.trigger
        ).transform(
            UNCONFINED,
            flow3
        ) { f1: Int, f2: Int ->
            (f1 + 2 * f2).toString()
        }
        Input2(
            zip(flow2),
            zip(flow5)
        ).transform(
            UNCONFINED,
            flow4
        ) { f2: Int, f3: String ->
            if (f2 > 0) f3.repeat(f2) else ("a" + "b".repeat(1 - f2))
        }

        var numReceived3 = 0
        val job3 = launch(Dispatchers.Unconfined) {
            val turbine3 = flow3.testIn(this)
            // flow3 is already hot, collect its current value
            assertEquals("-999", turbine3.awaitItem())
            numReceived3++
            repeat(numTriggers) { i ->
                assertEquals("${i + 2 * (i - 1)}", turbine3.awaitItem())
                numReceived3++

                assertEquals("${3 * i}", turbine3.awaitItem())
                numReceived3++
            }
            assertEquals(listOf(), turbine3.cancelAndConsumeRemainingEvents())
        }

        var numReceived4 = 0
        val job4 = launch(Dispatchers.Unconfined) {
            val turbine4 = flow4.testIn(this)
            for (i in -1 until numTriggers) {
                val exp = if (i > 0) "${-1 * i}".repeat(i) else ("a" + "b".repeat(1 - i))
                val got = turbine4.awaitItem()
                assertEquals(exp, got)
                numReceived4++
            }
            assertEquals(listOf(), turbine4.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow1.emit(i)
            flow2.emit(i)
        }

        job3.join()   // Wait for the job to finish
        assertEquals(numReceived3, 2 * numTriggers + 1)
        job4.join()
        assertEquals(numReceived4, numTriggers + 1)
        job3.cancel()
        job4.cancel()
    }
    //endregion
}
