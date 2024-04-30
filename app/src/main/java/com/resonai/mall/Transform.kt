package com.resonai.mall

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun <T, R> SharedFlow<T>.transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (T) -> R
): Job {
    return scope.launch {
        map(op).collect {
            to.emit(it)
        }
    }
}