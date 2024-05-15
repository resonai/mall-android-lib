package com.resonai.mall

import app.cash.turbine.testIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.math.max
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

    @Test
    fun updaterTest() = runTest {
        val flow1 = MallNode(-1)
        val flow2 = MallNode("")
        val numTriggers = 30
        var numReceived = 0
        val MAX_STRING_LENGTH = 30
        val scope = CoroutineScope(Dispatchers.Unconfined)
        val updater1 = { i: Int ->
            { j: String ->
                val res = if (i > 0 && j.length < MAX_STRING_LENGTH) j.repeat(i) else "$i"
                res
            }
        }
        scope.up(flow1.trigger, updater1, flow2)
        val job = scope.launch {
            val turbine = flow2.testIn(this)
            assertEquals("-1", turbine.awaitItem())
            numReceived++
            var numberString = "0"
            for (it in 0 until numTriggers) {
                numberString = numberString.repeat(if (it > 0) it else 1)
                if (numberString.length > MAX_STRING_LENGTH * if (it > 0) it else 1) numberString = "$it"
                assertEquals(numberString, turbine.awaitItem())
                numReceived++
            }
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }

        repeat(numTriggers) { i ->
            flow1.emit(i)
        }
        job.join()
        assertEquals(numTriggers + 1, numReceived)
        job.cancel()
    }

    @Test
    fun multiUpdaterTest() = runTest {
        val flow1 = MallNode(0)
        val flow2 = MallNode(0)
        val flow3 = MallNode(0)
        val flow4 = MallNode(0)
        val numTriggers = 100
        var numReceived = 0
        val scope = CoroutineScope(Dispatchers.Unconfined)
        val updater = { i: Int, j: Int, k: Int ->
            { l: Int ->
                l + max(i, max(j, k))
            }
        }
        scope.up(flow1.trigger, flow2.trigger, flow3.trigger, updater, flow4)
        val job = scope.launch {
            val turbine = flow4.testIn(this)
            var total = 0
            assertEquals(total, turbine.awaitItem())
            numReceived++
            for (it in 1..numTriggers) {
                total += it
                val turbineGot = turbine.awaitItem()
                assertEquals(total, turbineGot)
                numReceived++
            }
            assertEquals(listOf(), turbine.cancelAndConsumeRemainingEvents())
        }
        repeat(numTriggers) {
            val ourFlow = when ((1..3).random()) {
                1 -> flow1
                2 -> flow2
                3 -> flow3
                else -> throw AssertionError("Impossible is nothing!")
            }
            ourFlow.emit(it + 1)
        }
        job.join()
        assertEquals(numTriggers + 1, numReceived)
        job.cancel()
    }
}
