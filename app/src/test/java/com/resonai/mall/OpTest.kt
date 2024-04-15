package com.resonai.mall

import app.cash.turbine.testIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

fun <A, B> pairThem(a: A, b: B): Pair<A, B> = Pair(a, b)

class OpTest {

    @Test
    fun inputChainTest() = runTest {
        val numTriggers = 10
        var numReceived = 0
        val flow1 = MallNode(0)
        val flow2 = MallNode("a")
        val flow3 = MallNode(Pair(0, "a"))
        val scope = CoroutineScope(Dispatchers.Unconfined)
        scope.op(
            flow1.trigger,
            flow2.reads,
            ::pairThem,
            flow3
        )
        val job = scope.launch {
            val turbine = flow3.testIn(this)
            assertEquals(Pair(0, "a"), (turbine.awaitItem() as Pair<*, *>))
            numReceived++
            repeat(numTriggers) {
                assertEquals(Pair(it, it.toString()), (turbine.awaitItem() as Pair<*, *>))
                numReceived++
            }
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow2.emit(i.toString())
            flow1.emit(i)
        }
        job.join()
        assertEquals(numReceived, numTriggers + 1)
        job.cancel()
    }

    @Test
    fun possibleResultsTest() = runTest {
        val numTriggers = 50
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode(-1)
        val flow3 = MallNode(-4)
        val scope = CoroutineScope(Dispatchers.Unconfined)
        scope.opp(
            flow1.trigger,
            flow2.trigger,
            { a: Int, b: Int -> PossibleResult((a + b) % 4 == 0, a + b) },
            flow3
        )
        val job = scope.launch {
            val turbine = flow3.testIn(this)
            assertEquals(-4, turbine.awaitItem())
            numReceived++
            for (it in 0 until numTriggers / 2) {
                assertEquals(4 * it, turbine.awaitItem())
                numReceived++
            }
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow2.emit(i)
            flow1.emit(i)
        }
        job.join()
        assertEquals(numTriggers / 2 + 1, numReceived)
        job.cancel()
    }

    @Test
    fun initialSubscriptionTest() = runTest {
        val numTriggers = 2
        var numReceived = 0
        val flow1 = MallNode(-1)
        val flow2 = MallNode("null")
        val flow3 = MallNode("EMPTY")
        val scope = CoroutineScope(Dispatchers.Unconfined)
        scope.opp(
            flow1.trigger,
            flow2.trigger,
            { a: Int, b: String -> PossibleResult(true, "$a:$b") },
            flow3
        )
        val job = scope.launch {
            val turbine = flow3.testIn(this)
            assertEquals("EMPTY", turbine.awaitItem())
            numReceived++
            assertEquals("-1:A", turbine.awaitItem())
            numReceived++
            assertEquals("0:A", turbine.awaitItem())
            numReceived++
            assertEquals("0:B", turbine.awaitItem())
            numReceived++
            assertEquals("1:B", turbine.awaitItem())
            numReceived++
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow2.emit(('A' + i).toString())
            flow1.emit(i)
        }
        job.join()
        assertEquals(numReceived, numTriggers * 2 + 1)
        job.cancel()
    }
}
