package com.resonai.mall.ext

import com.resonai.mall.FlowsInLike
import com.resonai.mall.InFlow
import com.resonai.mall.MallNode

fun <T> T.asReadsMallNode(inType: FlowsInLike = FlowsInLike.READS): InFlow<T> {
    return InFlow(MallNode(this), inType)
}

val devNull = MallNode(Unit)
