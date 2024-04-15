package com.resonai.mall

import kotlinx.coroutines.flow.*

internal class InputsBuilder {
    val flows: MutableList<InFlow<*>> = ArrayList()
//  val ktypes: MutableList<KType> = ArrayList()

    fun <F> addInput(flow: SharedFlow<F>, type: FlowsInLike): InputsBuilder {
        flows.add(InFlow(flow, type))
        return this
    }

    fun <F> addInput(inFlow: InFlow<F>): InputsBuilder {
        // Ugly, but see https://discuss.kotlinlang.org/t/error-override-by-a-function-with-reified-type-parameter/9730/2
        // for why we can't use a reified inline function
        addInput(inFlow.flow, inFlow.inType)
        return this
    }

    fun build(): Flow<List<*>> {
        val triggerFlows = gatherOfInType(FlowsInLike.TRIGGER)
        val hasTriggers = triggerFlows != null
        val zipFlows = gatherOfInType(FlowsInLike.ZIP)
        val hasZips = zipFlows != null
        val readFlows = gatherOfInType(FlowsInLike.READS)
        val hasReads = readFlows != null
        return when (Triple(hasTriggers, hasZips, hasReads)) {
            Triple(true, true, true) -> {
                val combined: Flow<Triple<List<*>, List<*>, List<*>>> = triggersReadsZips(
                    triggerFlows!!.first, triggerFlows.second,
                    readFlows!!.first, readFlows.second,
                    zipFlows!!.first, zipFlows.second
                )
                combined.map {
                    it.first + it.third + it.second
                }
            }

            Triple(true, false, true) -> {
                val combined: Flow<Pair<List<*>, List<*>>> = triggersAndReads(
                    triggerFlows!!.first, triggerFlows.second,
                    readFlows!!.first, readFlows.second
                )
                combined.map {
                    it.first + it.second
                }
            }

            Triple(true, false, false) -> triggerFlows!!.first
            Triple(true, true, false) -> {
                val combined: Flow<Pair<List<*>, List<*>>> = triggersZips(
                    triggerFlows!!.first, zipFlows!!.first
                )
                combined.map {
                    it.first + it.second
                }
            }

            Triple(false, true, true) -> {
                val combined: Flow<Pair<List<*>, List<*>>> = triggersAndReads(
                    zipFlows!!.first, zipFlows.second, readFlows!!.first, readFlows.second
                )
                combined.map {
                    it.first + it.second
                }
            }

            Triple(false, true, false) -> zipFlows!!.first

            //      Triple(false, false, true),
            //      Triple(false, false, false),
            else -> throw java.lang.IllegalArgumentException(
                "Oops, something went wrong in your list of inputs! $flows"
            )
        }.drop(1)
    }

    private fun gatherOfInType(inType: FlowsInLike): Pair<Flow<List<*>>, List<*>>? {
        val gatheredFlows: List<InFlow<*>> = flows
            .filter { it.inType == inType }
        if (gatheredFlows.isEmpty()) {
            return null
        }

        val listOfFlows: List<Flow<*>> = gatheredFlows.map { it.flow }
        val firsts: List<*> = gatheredFlows.map { it.flow.value }
        val flowList: Flow<List<*>> = if (inType == FlowsInLike.ZIP) zip(listOfFlows) { it }
        else combine(listOfFlows) { it.toList() }
        return Pair(flowList, firsts)
    }
}


interface InputAddable<out Q> {
    fun <U> addInput(inFlow: InFlow<U>): InputAddable<*>
    fun build(): Flow<Q>
}

class InputsBuilderEmpty : InputAddable<Nothing> {
    override fun <T1> addInput(inFlow: InFlow<T1>): InputsBuilder1<T1> = InputsBuilder1(inFlow)

    //fun buildUnsafe(): Flow<List<*>> = build().map { listOf(it) }
    override fun build(): Flow<Nothing> = emptyFlow()
//  override fun <R> build(through: (Any?) -> R): Flow<R> = flow { emit(through(null)) }
}

class InputsBuilder1<T1>(val inFlow1: InFlow<T1>) : InputAddable<T1> {
    override fun <T2> addInput(inFlow: InFlow<T2>): InputsBuilder2<T1, T2> =
        InputsBuilder2(inFlow1, inFlow)

    private fun buildUnsafe(): Flow<List<*>> = build().map { listOf(it) }
    override fun build(): Flow<T1> {
        require(inFlow1.inType == FlowsInLike.TRIGGER) { "Single flow must be trigger" }
        return inFlow1.flow
    }
}
