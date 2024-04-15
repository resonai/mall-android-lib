package com.resonai.mall

import kotlinx.coroutines.flow.*

private data class PairInputsState<T, R>(
    val shouldEmit: Boolean,
    val triggers: IndexedValue<T>,
    val reads: IndexedValue<R>
)

fun <T, R> triggersAndReads(
    triggering: SharedFlow<T>,
    reading: SharedFlow<R>,
): Flow<Pair<T, R>> {
    return triggersAndReads(
        triggering, triggering.value,
        reading, reading.value
    )
}

fun <T, R> triggersAndReads(
    triggering: Flow<T>,
    firstTrigger: T,
    reading: Flow<R>,
    firstRead: R,
): Flow<Pair<T, R>> {
    return combine(triggering.withIndex(), reading.withIndex()) { x, y ->
        Pair(x, y)
    }.scan(
        PairInputsState(
            false,
            IndexedValue(-1, firstTrigger),
            IndexedValue(-1, firstRead)
        )
    ) { state, update ->
        val triggersUpdated = state.triggers.index != update.first.index
        // TODO (SimonencoL) I think this log we can hide to reduce unnecessary logs.
        //println("state will trigger $triggersUpdated - was $state, got $update")
        PairInputsState(triggersUpdated, update.first, update.second)
    }.filter { state ->
        state.shouldEmit
    }.map { state ->
        Pair(state.triggers.value, state.reads.value)
    }
}

// TODO(ShirL) For consistency, make this triggersZipsReads
fun <T, R, Z> triggersReadsZips(
    triggering: Flow<T>,
    firstTrigger: T,
    reading: Flow<R>,
    firstRead: R,
    zipping: Flow<Z>,
    firstZip: Z
): Flow<Triple<T, R, Z>> {
    return triggersAndReads(
        triggering.combine(zipping) { t, z ->
            Pair(t, z)
        },
        Pair(firstTrigger, firstZip),
        reading,
        firstRead
    ).map {
        Triple(it.first.first, it.second, it.first.second)
    }
}

fun <T, R, Z> triggersReadsZips(
    triggering: SharedFlow<T>,
    reading: SharedFlow<R>,
    zipping: SharedFlow<Z>
): Flow<Triple<T, R, Z>> {
    return triggersReadsZips(
        triggering, triggering.value,
        reading, reading.value,
        zipping, zipping.value
    )
}

fun <T, Z> triggersZips(
    triggering: Flow<T>,
    zipping: Flow<Z>
): Flow<Pair<T, Z>> {
    return triggering.combine(zipping) { t, z ->
        Pair(t, z)
    }
}

inline fun <reified T, R> zip(
    flows: Iterable<Flow<T>>,
    crossinline transform: suspend (List<T>) -> R
): Flow<R> {

    val flowList = flows.toList()
    var zipFlow: Flow<ArrayList<T>> = flowList[0].zip(flowList[1]) { z1, z2 ->
        arrayListOf(z1, z2)
    }
    flowList.asSequence().drop(2).forEach { nextFlow ->
        zipFlow = zipFlow.zip(nextFlow) { zz, z ->
            zz.add(z)
            return@zip zz
        }
    }
    return zipFlow.map { transform(it.toList()) }
}
