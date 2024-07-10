package com.resonai.mall

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun <T> MallNode(initial: T): MutableSharedFlow<T> {
    val shared: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    val didEmit = shared.tryEmit(initial)
    // println("emit $initial - $didEmit")
    return shared
}

inline fun <T> MutableSharedFlow<T>.update(function: (T) -> T) {
    synchronized(this) {
        // TODO[ShirL) Confirm that this implementation is sufficiently efficient vs
        // the implementation in StateFlow
        // (https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/common/src/flow/StateFlow.kt#L321)
        // or use of Mutex
        // (https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/)
        val prevValue = value
        val nextValue = function(prevValue)
        tryEmit(nextValue)
    }
}

inline fun <T> MutableSharedFlow<T>.possibleUpdate(function: (T) -> PossibleResult<T>) {
    synchronized(this) {
        val prevValue = value
        val nextValue = function(prevValue)
        if (nextValue.shouldEmit) {
            tryEmit(nextValue.result)
        }
    }
}

val <T> SharedFlow<T>.value: T
    get() = replayCache.last()

fun <T> Flow<T>.mallIn(
    scope: CoroutineScope
): SharedFlow<T> {
    return shareIn(scope, SharingStarted.Eagerly, 1)
}
//endregion


//region INPUT FLOW
enum class FlowsInLike {
    TRIGGER, READS, ZIP
}

data class InFlow<F>(
    val flow: SharedFlow<F>,
    val inType: FlowsInLike
)

val <F> SharedFlow<F>.trigger: InFlow<F>
    get() = InFlow(this, FlowsInLike.TRIGGER)

fun <F> reads(flow: SharedFlow<F>): InFlow<F> {
    return InFlow(flow, FlowsInLike.READS)
}

val <F> SharedFlow<F>.reads: InFlow<F>
    get() = reads(this)

fun <F> zip(flow: SharedFlow<F>): InFlow<F> {
    return InFlow(flow, FlowsInLike.ZIP)
}

val <F> SharedFlow<F>.zip: InFlow<F>
    get() = zip(this)

val trigger = FlowsInLike.TRIGGER
val reads = FlowsInLike.READS
val zip = FlowsInLike.ZIP
infix fun <F> SharedFlow<F>.inas(like: FlowsInLike): InFlow<F> {
    return InFlow(this, like)
}
