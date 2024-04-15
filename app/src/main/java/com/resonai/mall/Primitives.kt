package com.resonai.mall

import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun tupleTypeForN(
    n: Int, typeArguments: List<TypeName>
): ParameterizedTypeName {
    require(n in 2..MAX_INPUTS)
    return when (n) {
        2 -> Pair::class.asClassName().parameterizedBy(typeArguments)
        3 -> Triple::class.asClassName().parameterizedBy(typeArguments)
        4 -> Quadruple::class.asClassName().parameterizedBy(typeArguments)
        5 -> Quintuple::class.asClassName().parameterizedBy(typeArguments)
        6 -> Sextuple::class.asClassName().parameterizedBy(typeArguments)
        7 -> Septuple::class.asClassName().parameterizedBy(typeArguments)
        8 -> Octuple::class.asClassName().parameterizedBy(typeArguments)
        9 -> Ninetuple::class.asClassName().parameterizedBy(typeArguments)
        10 -> Tentuple::class.asClassName().parameterizedBy(typeArguments)
        11 -> Eleventuple::class.asClassName().parameterizedBy(typeArguments)
        12 -> Twelvetuple::class.asClassName().parameterizedBy(typeArguments)
        13 -> Thirteentuple::class.asClassName().parameterizedBy(typeArguments)
        14 -> Fourteentuple::class.asClassName().parameterizedBy(typeArguments)
        15 -> Fifteentuple::class.asClassName().parameterizedBy(typeArguments)
        16 -> Sixteentuple::class.asClassName().parameterizedBy(typeArguments)
        else -> {
            throw java.lang.IndexOutOfBoundsException("Not implemented yet")
        }
    }
}
//endregion


//region MALL NODE
fun <T> MallNode(initial: T): MutableSharedFlow<T> {
    val shared: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.SUSPEND,
    )
    val didEmit = shared.tryEmit(initial)
    // println("emit $initial - $didEmit")
    return shared
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
//endregion