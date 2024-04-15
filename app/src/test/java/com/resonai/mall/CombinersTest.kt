package com.resonai.mall

import app.cash.turbine.testIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class CombinersTest {

    @Test
    // See https://github.com/Kotlin/kotlinx.coroutines/issues/2082#issuecomment-639652477 for explanation of structure
    fun triggersAndReadsTest() = runTest {
        val numTriggers = 5
        val numReads = 6
        var numReceived = 0
        val flow1 = MallNode("-1")
        val flow2 = MallNode(-1)
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = triggersAndReads(
                flow1,
                flow2
            ).stateIn(this)
            val turbine = flow3.testIn(this)
            assertEquals(Pair("-1", -1), (turbine.awaitItem() as Pair<*, *>))
            numReceived++
            repeat(numTriggers) { i ->
                assertEquals(Pair("$i", numReads - 1), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            repeat(numReads) { j ->
                flow2.emit(j)
            }
            flow1.emit("$i")
        }

        assertEquals(numReceived, numTriggers + 1)
        job.cancel()
    }

    @Test
    fun triggersReadsZipsTest() = runTest {
        val numTriggers = 10
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode("-1")
        val flow3a = MallNode(-1)
        val flow3b = MallNode(-1)
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = flow3a.zip(flow3b) { a, b ->
                ('a' + a).toString() + ('A' + b).toString()
            }.stateIn(this)
            val flow4 = triggersReadsZips(flow1, flow2, flow3)
            val turbine = flow4.testIn(this)
            assertEquals(Triple(-1, "-1", "`@"), (turbine.awaitItem() as Triple<*, *, *>))
            numReceived++
            repeat(2 * numTriggers) { i ->
                if (i < numTriggers) {
                    assertEquals(
                        Triple(
                            i,
                            (i - 1).toString(),
                            ('a' + i - 1).toString() + ('A' + i - 1).toString()
                        ), (turbine.awaitItem() as Triple<*, *, *>)
                    )
                    numReceived++
                    assertEquals(
                        Triple(
                            i,
                            i.toString(),
                            ('a' + i).toString() + ('A' + i).toString()
                        ), (turbine.awaitItem() as Triple<*, *, *>)
                    )
                } else {
                    assertEquals(
                        Triple(
                            numTriggers - 1,
                            (numTriggers - 1).toString(),
                            ('a' + i).toString() + ('A' + i).toString()
                        ), (turbine.awaitItem() as Triple<*, *, *>)
                    )
                }
                numReceived++
            }

            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }
        repeat(numTriggers) { i ->
            flow1.emit(i)
            flow2.emit("$i")
            flow3a.emit(i)
            flow3b.emit(i)
        }
        // Test alternating zips
        repeat(numTriggers) { i ->
            val j = numTriggers + i
            if (j % 2 == 0) {
                flow3a.emit(j)
                flow3a.emit(j + 1)
            } else {
                flow3b.emit(j - 1)
                flow3b.emit(j)
            }
        }

        assertEquals(numReceived, 3 * numTriggers + 1)
        job.cancel()
    }

    @Test
    fun zipsTest() = runTest {
        val numTriggers = 20
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode(-1)
        val job = launch(Dispatchers.Unconfined) {
            val flow3 = zip(listOf(flow1, flow2)) {
                it[0] + it[1]
            }.stateIn(this)
            val turbine = flow3.testIn(this)
            assertEquals(-2, turbine.awaitItem())
            numReceived++
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

        assertEquals(numReceived, numTriggers + 1)
        job.cancel()
    }

    @Test
    fun zips3Test() = runTest {
        val numTriggers = 10
        var numReceived = 0
        val flow1 = MallNode(0)
        val flow2 = MallNode(0)
        val flow3 = MallNode(0)
        val job = launch(Dispatchers.Unconfined) {
            val flowZip = zip(listOf(flow1, flow2, flow3)) {
                it[0].toString() + it[1].toString() + it[2].toString()
            }.stateIn(this)
            val turbine = flowZip.testIn(this)
            for (i in 0..numTriggers) {
                assertEquals("$i$i$i", turbine.awaitItem())
                numReceived++
            }
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        for (i in 1..numTriggers) {
            if (i % 2 == 1) {
                flow1.emit(i)
                flow1.emit(i + 1)
            } else {
                flow2.emit(i - 1)
                flow2.emit(i)
            }
            flow3.emit(i)
        }

        assertEquals(numReceived, numTriggers + 1)
        job.cancel()
    }

    @Test
    fun zipsNTest() = runTest {
        for (numFlows in 2..9) {
            val numTriggers = numFlows * 4  // ensure everything divides
            var numReceived = 0
            val flows = (1..numFlows).map { MallNode(0) }
            val job = launch(Dispatchers.Unconfined) {
                val flowZip = zip(flows) {
                    it.joinToString()
                }.stateIn(this)
                val turbine = flowZip.testIn(this)
                for (i in 0..numTriggers) {
                    assertEquals(
                        (1..numFlows).map { "$i" }.joinToString(),
                        turbine.awaitItem()
                    )
                    numReceived++
                }
                assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
            }

            for (i in 1..numTriggers) {
                flows.shuffled().forEach {
                    it.emit(i)
                }
            }

            assertEquals(numReceived, numTriggers + 1)
            job.cancel()
        }
    }
}
