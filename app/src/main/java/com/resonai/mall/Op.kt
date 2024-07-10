package com.resonai.mall

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PossibleResult<R>(
    val shouldEmit: Boolean,
    val result: R
)

@Suppress("UNCHECKED_CAST")
fun <R> StopResult() = PossibleResult(false, null as R)

//region function signatures
fun <T1, R> CoroutineScope.op(
    inFlow1: InFlow<T1>,
    transform: (T1) -> R,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow.map(transform).collect(out)
    }
}

fun <T1, R> CoroutineScope.opp(
    inFlow1: InFlow<T1>,
    transform: (T1) -> PossibleResult<R>,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow.map(transform).collect {
            if (it.shouldEmit) {
                out.emit(it.result)
            }
        }
    }
}

fun <T1, R> CoroutineScope.up(
    inFlow1: InFlow<T1>,
    transform: (T1) -> (R) -> R,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow.map(transform).collect {
            out.update(it)
        }
    }
}

fun <T1, R> CoroutineScope.upp(
    inFlow1: InFlow<T1>,
    transform: (T1) -> (R) -> PossibleResult<R>,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow
            .map(transform)
            .collect {
                out.possibleUpdate(it)
            }
    }
}

fun <T1, R> CoroutineScope.dop(
    inFlow1: InFlow<T1>,
    delayMsTime: Long = 0,
    transform: (T1) -> R,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow.map {
            delay(delayMsTime)
            transform(it)
        }.collect(out)
    }
}

fun <T1, R> CoroutineScope.dopp(
    inFlow1: InFlow<T1>,
    delayMsTime: Long = 0,
    transform: (T1) -> PossibleResult<R>,
    out: MutableSharedFlow<R>
) {
    launch {
        inFlow1.flow.map {
            delay(delayMsTime)
            transform(it)
        }.collect {
            if (it.shouldEmit) {
                out.emit(it.result)
            }
        }
    }
}

//endregion
