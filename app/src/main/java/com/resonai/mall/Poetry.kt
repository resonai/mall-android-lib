package com.resonai.mall

import kotlin.collections.List
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

public data class Tuple2nd<out Element1st, out Element2nd>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
)

public data class Tuple3rd<out Element1st, out Element2nd, out Element3rd>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
)

public data class Tuple4th<out Element1st, out Element2nd, out Element3rd, out Element4th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
)

public data class Tuple5th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
)

public data class Tuple6th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
)

public data class Tuple7th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
)

public data class Tuple8th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
)

public data class Tuple9th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
)

public data class Tuple10th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
)

public data class Tuple11th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
)

public data class Tuple12th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
)

public data class Tuple13th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
)

public data class Tuple14th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
)

public data class Tuple15th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
)

public data class Tuple16th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th, out
    Element16th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
  public val element16th: Element16th,
)

public data class Tuple17th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th, out
    Element16th, out Element17th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
  public val element16th: Element16th,
  public val element17th: Element17th,
)

public data class Tuple18th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th, out
    Element16th, out Element17th, out Element18th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
  public val element16th: Element16th,
  public val element17th: Element17th,
  public val element18th: Element18th,
)

public data class Tuple19th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th, out
    Element16th, out Element17th, out Element18th, out Element19th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
  public val element16th: Element16th,
  public val element17th: Element17th,
  public val element18th: Element18th,
  public val element19th: Element19th,
)

public data class Tuple20th<out Element1st, out Element2nd, out Element3rd, out Element4th, out
    Element5th, out Element6th, out Element7th, out Element8th, out Element9th, out Element10th, out
    Element11th, out Element12th, out Element13th, out Element14th, out Element15th, out
    Element16th, out Element17th, out Element18th, out Element19th, out Element20th>(
  public val element1st: Element1st,
  public val element2nd: Element2nd,
  public val element3rd: Element3rd,
  public val element4th: Element4th,
  public val element5th: Element5th,
  public val element6th: Element6th,
  public val element7th: Element7th,
  public val element8th: Element8th,
  public val element9th: Element9th,
  public val element10th: Element10th,
  public val element11th: Element11th,
  public val element12th: Element12th,
  public val element13th: Element13th,
  public val element14th: Element14th,
  public val element15th: Element15th,
  public val element16th: Element16th,
  public val element17th: Element17th,
  public val element18th: Element18th,
  public val element19th: Element19th,
  public val element20th: Element20th,
)

/**
 * AUTOGEN(KotlinPoet) generateCombineTupleNFn(2)
 */
public fun <T1, T2> combineTupleGen(flow1: Flow<T1>, flow2: Flow<T2>): Flow<Tuple2nd<T1, T2>> =
    combine(flow1, flow2) { t1, t2 -> com.resonai.mall.Tuple2nd<T1, T2>(t1, t2) }

/**
 * AUTOGEN(KotlinPoet) generateCombineTupleNFn(3)
 */
public fun <T1, T2, T3> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
): Flow<Tuple3rd<T1, T2, T3>> = combine(flow1, flow2, flow3) { t1, t2, t3 ->
    com.resonai.mall.Tuple3rd<T1, T2, T3>(t1, t2, t3) }

/**
 * AUTOGEN(KotlinPoet) generateCombineTupleNFn(4)
 */
public fun <T1, T2, T3, T4> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
): Flow<Tuple4th<T1, T2, T3, T4>> = combine(flow1, flow2, flow3, flow4) { t1, t2, t3, t4 ->
    com.resonai.mall.Tuple4th<T1, T2, T3, T4>(t1, t2, t3, t4) }

/**
 * AUTOGEN(KotlinPoet) generateCombineTupleNFn(5)
 */
public fun <T1, T2, T3, T4, T5> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
): Flow<Tuple5th<T1, T2, T3, T4, T5>> = combine(flow1, flow2, flow3, flow4, flow5) { t1, t2, t3, t4,
    t5 -> com.resonai.mall.Tuple5th<T1, T2, T3, T4, T5>(t1, t2, t3, t4, t5) }

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(6, combine)
 */
public fun <T1, T2, T3, T4, T5, T6> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
): Flow<Tuple6th<T1, T2, T3, T4, T5, T6>> = combineTupleGen(flow1, flow2, flow3, flow4,
    flow5).combine(flow6) { z, z2 -> com.resonai.mall.Tuple6th<T1, T2, T3, T4, T5, T6>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(7, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
): Flow<Tuple7th<T1, T2, T3, T4, T5, T6, T7>> = combineTupleGen(flow1, flow2, flow3, flow4, flow5,
    flow6).combine(flow7) { z, z2 -> com.resonai.mall.Tuple7th<T1, T2, T3, T4, T5, T6,
    T7>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(8, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
): Flow<Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>> = combineTupleGen(flow1, flow2, flow3, flow4,
    flow5, flow6, flow7).combine(flow8) { z, z2 -> com.resonai.mall.Tuple8th<T1, T2, T3, T4, T5, T6,
    T7, T8>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(9, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
): Flow<Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>> = combineTupleGen(flow1, flow2, flow3, flow4,
    flow5, flow6, flow7, flow8).combine(flow9) { z, z2 -> com.resonai.mall.Tuple9th<T1, T2, T3, T4,
    T5, T6, T7, T8, T9>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(10, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
): Flow<Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> = combineTupleGen(flow1, flow2, flow3,
    flow4, flow5, flow6, flow7, flow8, flow9).combine(flow10) { z, z2 ->
    com.resonai.mall.Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(z.element1st, z.element2nd,
    z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th, z.element8th,
    z.element9th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(11, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
): Flow<Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> = combineTupleGen(flow1, flow2,
    flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10).combine(flow11) { z, z2 ->
    com.resonai.mall.Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th,
    z.element8th, z.element9th, z.element10th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(12, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
): Flow<Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> = combineTupleGen(flow1,
    flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11).combine(flow12) { z,
    z2 -> com.resonai.mall.Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11,
    T12>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z.element8th, z.element9th, z.element10th, z.element11th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(13, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
): Flow<Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> = combineTupleGen(flow1,
    flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11, flow12).combine(flow13)
    { z, z2 -> com.resonai.mall.Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12,
    T13>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z.element8th, z.element9th, z.element10th, z.element11th, z.element12th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(14, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
): Flow<Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> =
    combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13).combine(flow14) { z, z2 -> com.resonai.mall.Tuple14th<T1, T2, T3, T4, T5, T6,
    T7, T8, T9, T10, T11, T12, T13, T14>(z.element1st, z.element2nd, z.element3rd, z.element4th,
    z.element5th, z.element6th, z.element7th, z.element8th, z.element9th, z.element10th,
    z.element11th, z.element12th, z.element13th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(15, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
): Flow<Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> =
    combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14).combine(flow15) { z, z2 -> com.resonai.mall.Tuple15th<T1, T2, T3, T4,
    T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(z.element1st, z.element2nd, z.element3rd,
    z.element4th, z.element5th, z.element6th, z.element7th, z.element8th, z.element9th,
    z.element10th, z.element11th, z.element12th, z.element13th, z.element14th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(16, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
): Flow<Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> =
    combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15).combine(flow16) { z, z2 -> com.resonai.mall.Tuple16th<T1, T2,
    T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(z.element1st, z.element2nd,
    z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th, z.element8th,
    z.element9th, z.element10th, z.element11th, z.element12th, z.element13th, z.element14th,
    z.element15th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(17, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>
    combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
): Flow<Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> =
    combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16).combine(flow17) { z, z2 ->
    com.resonai.mall.Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z.element8th, z.element9th, z.element10th, z.element11th, z.element12th,
    z.element13th, z.element14th, z.element15th, z.element16th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(18, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
    combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
): Flow<Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
    = combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17).combine(flow18) { z, z2 ->
    com.resonai.mall.Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(19, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>
    combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
  flow19: Flow<T19>,
):
    Flow<Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>>
    = combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17, flow18).combine(flow19) { z, z2 ->
    com.resonai.mall.Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18, T19>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th,
    z.element18th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(20, combine)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19,
    T20> combineTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
  flow19: Flow<T19>,
  flow20: Flow<T20>,
):
    Flow<Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>>
    = combineTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17, flow18, flow19).combine(flow20) { z, z2 ->
    com.resonai.mall.Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18, T19, T20>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th,
    z.element18th, z.element19th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(2, zip)
 */
public fun <T1, T2> zipTupleGen(flow1: Flow<T1>, flow2: Flow<T2>): Flow<Tuple2nd<T1, T2>> =
    flow1.zip(flow2) { z1, z2 -> Tuple2nd(z1, z2) }

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(3, zip)
 */
public fun <T1, T2, T3> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
): Flow<Tuple3rd<T1, T2, T3>> = zipTupleGen(flow1, flow2).zip(flow3) { z, z2 ->
    com.resonai.mall.Tuple3rd<T1, T2, T3>(z.element1st, z.element2nd, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(4, zip)
 */
public fun <T1, T2, T3, T4> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
): Flow<Tuple4th<T1, T2, T3, T4>> = zipTupleGen(flow1, flow2, flow3).zip(flow4) { z, z2 ->
    com.resonai.mall.Tuple4th<T1, T2, T3, T4>(z.element1st, z.element2nd, z.element3rd, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(5, zip)
 */
public fun <T1, T2, T3, T4, T5> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
): Flow<Tuple5th<T1, T2, T3, T4, T5>> = zipTupleGen(flow1, flow2, flow3, flow4).zip(flow5) { z,
    z2 -> com.resonai.mall.Tuple5th<T1, T2, T3, T4, T5>(z.element1st, z.element2nd, z.element3rd,
    z.element4th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(6, zip)
 */
public fun <T1, T2, T3, T4, T5, T6> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
): Flow<Tuple6th<T1, T2, T3, T4, T5, T6>> = zipTupleGen(flow1, flow2, flow3, flow4,
    flow5).zip(flow6) { z, z2 -> com.resonai.mall.Tuple6th<T1, T2, T3, T4, T5, T6>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(7, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
): Flow<Tuple7th<T1, T2, T3, T4, T5, T6, T7>> = zipTupleGen(flow1, flow2, flow3, flow4, flow5,
    flow6).zip(flow7) { z, z2 -> com.resonai.mall.Tuple7th<T1, T2, T3, T4, T5, T6, T7>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(8, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
): Flow<Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>> = zipTupleGen(flow1, flow2, flow3, flow4, flow5,
    flow6, flow7).zip(flow8) { z, z2 -> com.resonai.mall.Tuple8th<T1, T2, T3, T4, T5, T6, T7,
    T8>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(9, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
): Flow<Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>> = zipTupleGen(flow1, flow2, flow3, flow4,
    flow5, flow6, flow7, flow8).zip(flow9) { z, z2 -> com.resonai.mall.Tuple9th<T1, T2, T3, T4, T5,
    T6, T7, T8, T9>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(10, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
): Flow<Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> = zipTupleGen(flow1, flow2, flow3,
    flow4, flow5, flow6, flow7, flow8, flow9).zip(flow10) { z, z2 -> com.resonai.mall.Tuple10th<T1,
    T2, T3, T4, T5, T6, T7, T8, T9, T10>(z.element1st, z.element2nd, z.element3rd, z.element4th,
    z.element5th, z.element6th, z.element7th, z.element8th, z.element9th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(11, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
): Flow<Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> = zipTupleGen(flow1, flow2, flow3,
    flow4, flow5, flow6, flow7, flow8, flow9, flow10).zip(flow11) { z, z2 ->
    com.resonai.mall.Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th,
    z.element8th, z.element9th, z.element10th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(12, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
): Flow<Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> = zipTupleGen(flow1, flow2,
    flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11).zip(flow12) { z, z2 ->
    com.resonai.mall.Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th,
    z.element8th, z.element9th, z.element10th, z.element11th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(13, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
): Flow<Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> = zipTupleGen(flow1,
    flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11, flow12).zip(flow13) { z,
    z2 -> com.resonai.mall.Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12,
    T13>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th,
    z.element7th, z.element8th, z.element9th, z.element10th, z.element11th, z.element12th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(14, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
): Flow<Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> = zipTupleGen(flow1,
    flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11, flow12,
    flow13).zip(flow14) { z, z2 -> com.resonai.mall.Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9,
    T10, T11, T12, T13, T14>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(15, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
): Flow<Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> =
    zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14).zip(flow15) { z, z2 -> com.resonai.mall.Tuple15th<T1, T2, T3, T4, T5,
    T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(z.element1st, z.element2nd, z.element3rd,
    z.element4th, z.element5th, z.element6th, z.element7th, z.element8th, z.element9th,
    z.element10th, z.element11th, z.element12th, z.element13th, z.element14th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(16, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
): Flow<Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> =
    zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15).zip(flow16) { z, z2 -> com.resonai.mall.Tuple16th<T1, T2, T3,
    T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(z.element1st, z.element2nd,
    z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th, z.element8th,
    z.element9th, z.element10th, z.element11th, z.element12th, z.element13th, z.element14th,
    z.element15th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(17, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
): Flow<Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> =
    zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16).zip(flow17) { z, z2 -> com.resonai.mall.Tuple17th<T1,
    T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>(z.element1st,
    z.element2nd, z.element3rd, z.element4th, z.element5th, z.element6th, z.element7th,
    z.element8th, z.element9th, z.element10th, z.element11th, z.element12th, z.element13th,
    z.element14th, z.element15th, z.element16th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(18, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
    zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
): Flow<Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
    = zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17).zip(flow18) { z, z2 ->
    com.resonai.mall.Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(19, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>
    zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
  flow19: Flow<T19>,
):
    Flow<Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>>
    = zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17, flow18).zip(flow19) { z, z2 ->
    com.resonai.mall.Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18, T19>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th,
    z.element18th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateCombinerTupleNFn(20, zip)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19,
    T20> zipTupleGen(
  flow1: Flow<T1>,
  flow2: Flow<T2>,
  flow3: Flow<T3>,
  flow4: Flow<T4>,
  flow5: Flow<T5>,
  flow6: Flow<T6>,
  flow7: Flow<T7>,
  flow8: Flow<T8>,
  flow9: Flow<T9>,
  flow10: Flow<T10>,
  flow11: Flow<T11>,
  flow12: Flow<T12>,
  flow13: Flow<T13>,
  flow14: Flow<T14>,
  flow15: Flow<T15>,
  flow16: Flow<T16>,
  flow17: Flow<T17>,
  flow18: Flow<T18>,
  flow19: Flow<T19>,
  flow20: Flow<T20>,
):
    Flow<Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>>
    = zipTupleGen(flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9, flow10, flow11,
    flow12, flow13, flow14, flow15, flow16, flow17, flow18, flow19).zip(flow20) { z, z2 ->
    com.resonai.mall.Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15,
    T16, T17, T18, T19, T20>(z.element1st, z.element2nd, z.element3rd, z.element4th, z.element5th,
    z.element6th, z.element7th, z.element8th, z.element9th, z.element10th, z.element11th,
    z.element12th, z.element13th, z.element14th, z.element15th, z.element16th, z.element17th,
    z.element18th, z.element19th, z2)}

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(2)
 */
public fun <T1, T2, R> Tuple2nd<T1, T2>.throughGen(fn: (p1: T1, p2: T2) -> R): R = fn(element1st,
    element2nd)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(3)
 */
public fun <T1, T2, T3, R> Tuple3rd<T1, T2, T3>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
) -> R): R = fn(element1st, element2nd, element3rd)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(4)
 */
public fun <T1, T2, T3, T4, R> Tuple4th<T1, T2, T3, T4>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
) -> R): R = fn(element1st, element2nd, element3rd, element4th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(5)
 */
public fun <T1, T2, T3, T4, T5, R> Tuple5th<T1, T2, T3, T4, T5>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(6)
 */
public fun <T1, T2, T3, T4, T5, T6, R> Tuple6th<T1, T2, T3, T4, T5, T6>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(7)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, R> Tuple7th<T1, T2, T3, T4, T5, T6, T7>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(8)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, R>
    Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(9)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R>
    Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(10)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>
    Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(11)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R>
    Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(12)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R>
    Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(13)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R>
    Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(14)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R>
    Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(15)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>
    Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(16)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R>
    Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
  p16: T16,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th, element16th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(17)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R>
    Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
  p16: T16,
  p17: T17,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th, element16th, element17th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(18)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R>
    Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
  p16: T16,
  p17: T17,
  p18: T18,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th, element16th, element17th, element18th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(19)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R>
    Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
  p16: T16,
  p17: T17,
  p18: T18,
  p19: T19,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th, element16th, element17th, element18th, element19th)

/**
 * AUTOGEN(KotlinPoet) generateTupleNFnThrough(20)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19,
    T20, R>
    Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>.throughGen(fn: (
  p1: T1,
  p2: T2,
  p3: T3,
  p4: T4,
  p5: T5,
  p6: T6,
  p7: T7,
  p8: T8,
  p9: T9,
  p10: T10,
  p11: T11,
  p12: T12,
  p13: T13,
  p14: T14,
  p15: T15,
  p16: T16,
  p17: T17,
  p18: T18,
  p19: T19,
  p20: T20,
) -> R): R = fn(element1st, element2nd, element3rd, element4th, element5th, element6th, element7th,
    element8th, element9th, element10th, element11th, element12th, element13th, element14th,
    element15th, element16th, element17th, element18th, element19th, element20th)

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(2)
 */
public class InputsBuilder2<T1, T2>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
) : InputAddable<Tuple2nd<T1, T2>> {
  public override fun <T3> addInput(inFlow: InFlow<T3>): InputsBuilder3<T1, T2, T3> =
      InputsBuilder3(inFlow1, inFlow2, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).build()

  public override fun build(): Flow<Tuple2nd<T1, T2>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple2nd(it[0] as T1, it[1] as T2)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(3)
 */
public class InputsBuilder3<T1, T2, T3>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
) : InputAddable<Tuple3rd<T1, T2, T3>> {
  public override fun <T4> addInput(inFlow: InFlow<T4>): InputsBuilder4<T1, T2, T3, T4> =
      InputsBuilder4(inFlow1, inFlow2, inFlow3, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).build()

  public override fun build(): Flow<Tuple3rd<T1, T2, T3>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple3rd(it[0] as T1, it[1] as T2, it[2] as T3)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(4)
 */
public class InputsBuilder4<T1, T2, T3, T4>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
) : InputAddable<Tuple4th<T1, T2, T3, T4>> {
  public override fun <T5> addInput(inFlow: InFlow<T5>): InputsBuilder5<T1, T2, T3, T4, T5> =
      InputsBuilder5(inFlow1, inFlow2, inFlow3, inFlow4, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).build()

  public override fun build(): Flow<Tuple4th<T1, T2, T3, T4>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple4th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(5)
 */
public class InputsBuilder5<T1, T2, T3, T4, T5>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
) : InputAddable<Tuple5th<T1, T2, T3, T4, T5>> {
  public override fun <T6> addInput(inFlow: InFlow<T6>): InputsBuilder6<T1, T2, T3, T4, T5, T6> =
      InputsBuilder6(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).build()

  public override fun build(): Flow<Tuple5th<T1, T2, T3, T4, T5>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple5th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(6)
 */
public class InputsBuilder6<T1, T2, T3, T4, T5, T6>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
) : InputAddable<Tuple6th<T1, T2, T3, T4, T5, T6>> {
  public override fun <T7> addInput(inFlow: InFlow<T7>): InputsBuilder7<T1, T2, T3, T4, T5, T6, T7>
      = InputsBuilder7(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).build()

  public override fun build(): Flow<Tuple6th<T1, T2, T3, T4, T5, T6>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple6th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(7)
 */
public class InputsBuilder7<T1, T2, T3, T4, T5, T6, T7>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
) : InputAddable<Tuple7th<T1, T2, T3, T4, T5, T6, T7>> {
  public override fun <T8> addInput(inFlow: InFlow<T8>):
      InputsBuilder8<T1, T2, T3, T4, T5, T6, T7, T8> = InputsBuilder8(inFlow1, inFlow2, inFlow3,
      inFlow4, inFlow5, inFlow6, inFlow7, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).build()

  public override fun build(): Flow<Tuple7th<T1, T2, T3, T4, T5, T6, T7>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple7th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(8)
 */
public class InputsBuilder8<T1, T2, T3, T4, T5, T6, T7, T8>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
) : InputAddable<Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>> {
  public override fun <T9> addInput(inFlow: InFlow<T9>):
      InputsBuilder9<T1, T2, T3, T4, T5, T6, T7, T8, T9> = InputsBuilder9(inFlow1, inFlow2, inFlow3,
      inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).build()

  public override fun build(): Flow<Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple8th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(9)
 */
public class InputsBuilder9<T1, T2, T3, T4, T5, T6, T7, T8, T9>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
) : InputAddable<Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>> {
  public override fun <T10> addInput(inFlow: InFlow<T10>):
      InputsBuilder10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> = InputsBuilder10(inFlow1, inFlow2,
      inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).build()

  public override fun build(): Flow<Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple9th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(10)
 */
public class InputsBuilder10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
) : InputAddable<Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> {
  public override fun <T11> addInput(inFlow: InFlow<T11>):
      InputsBuilder11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> = InputsBuilder11(inFlow1,
      inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9, inFlow10, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).build()

  public override fun build(): Flow<Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple10th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(11)
 */
public class InputsBuilder11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
) : InputAddable<Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> {
  public override fun <T12> addInput(inFlow: InFlow<T12>):
      InputsBuilder12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> = InputsBuilder12(inFlow1,
      inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9, inFlow10, inFlow11,
      inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).build()

  public override fun build(): Flow<Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple11th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(12)
 */
public class InputsBuilder12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
) : InputAddable<Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> {
  public override fun <T13> addInput(inFlow: InFlow<T13>):
      InputsBuilder13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> =
      InputsBuilder13(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).build()

  public override fun build(): Flow<Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple12th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(13)
 */
public class InputsBuilder13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
) : InputAddable<Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> {
  public override fun <T14> addInput(inFlow: InFlow<T14>):
      InputsBuilder14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> =
      InputsBuilder14(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).build()

  public override fun build():
      Flow<Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple13th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(14)
 */
public class InputsBuilder14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
) : InputAddable<Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> {
  public override fun <T15> addInput(inFlow: InFlow<T15>):
      InputsBuilder15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> =
      InputsBuilder15(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).build()

  public override fun build():
      Flow<Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple14th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(15)
 */
public class InputsBuilder15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
) : InputAddable<Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> {
  public override fun <T16> addInput(inFlow: InFlow<T16>):
      InputsBuilder16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> =
      InputsBuilder16(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).build()

  public override fun build():
      Flow<Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple15th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(16)
 */
public class InputsBuilder16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
) : InputAddable<Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> {
  public override fun <T17> addInput(inFlow: InFlow<T17>):
      InputsBuilder17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> =
      InputsBuilder17(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).build()

  public override fun build():
      Flow<Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple16th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15, it[15] as T16)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(17)
 */
public class InputsBuilder17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16,
    T17>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
) :
    InputAddable<Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>>
    {
  public override fun <T18> addInput(inFlow: InFlow<T18>):
      InputsBuilder18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>
      = InputsBuilder18(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17,
      inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).build()

  public override fun build():
      Flow<Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> =
      buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple17th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15, it[15] as T16, it[16] as T17)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(18)
 */
public class InputsBuilder18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16,
    T17, T18>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
) :
    InputAddable<Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
    {
  public override fun <T19> addInput(inFlow: InFlow<T19>):
      InputsBuilder19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>
      = InputsBuilder19(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17,
      inFlow18, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).build()

  public override fun build():
      Flow<Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
      = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple18th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15, it[15] as T16, it[16] as T17, it[17] as T18)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(19)
 */
public class InputsBuilder19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16,
    T17, T18, T19>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
  public val inFlow19: InFlow<T19>,
) :
    InputAddable<Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>>
    {
  public override fun <T20> addInput(inFlow: InFlow<T20>):
      InputsBuilder20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>
      = InputsBuilder20(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
      inFlow9, inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17,
      inFlow18, inFlow19, inFlow)

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).addInput(inFlow19).build()

  public override fun build():
      Flow<Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>>
      = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple19th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15, it[15] as T16, it[16] as T17, it[17] as T18,
      it[18] as T19)
  }

}

/**
 * AUTOGEN(KotlinPoet) generateInputsBuilderNClass(20)
 */
public class InputsBuilder20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16,
    T17, T18, T19, T20>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
  public val inFlow19: InFlow<T19>,
  public val inFlow20: InFlow<T20>,
) :
    InputAddable<Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>>
    {
  public override fun <T21> addInput(inFlow: InFlow<T21>): InputAddable<*> {
    TODO("Too many inputs")
  }

  private fun buildUnsafe(): Flow<List<*>> =
      InputsBuilder().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).addInput(inFlow19).addInput(inFlow20).build()

  public override fun build():
      Flow<Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>>
      = buildUnsafe().map {
  	@Suppress("UNCHECKED_CAST")
  	com.resonai.mall.Tuple20th(it[0] as T1, it[1] as T2, it[2] as T3, it[3] as T4, it[4] as T5, it[5]
      as T6, it[6] as T7, it[7] as T8, it[8] as T9, it[9] as T10, it[10] as T11, it[11] as T12,
      it[12] as T13, it[13] as T14, it[14] as T15, it[15] as T16, it[16] as T17, it[17] as T18,
      it[18] as T19, it[19] as T20)
  }

}

public class Input2<T1, T2>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
) {
  internal fun gather(): Flow<Tuple2nd<T1, T2>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (`1st`: T1, `2nd`: T2) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (`1st`: T1, `2nd`: T2) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input3<T1, T2, T3>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
) {
  internal fun gather(): Flow<Tuple3rd<T1, T2, T3>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input4<T1, T2, T3, T4>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
) {
  internal fun gather(): Flow<Tuple4th<T1, T2, T3, T4>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input5<T1, T2, T3, T4, T5>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
) {
  internal fun gather(): Flow<Tuple5th<T1, T2, T3, T4, T5>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input6<T1, T2, T3, T4, T5, T6>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
) {
  internal fun gather(): Flow<Tuple6th<T1, T2, T3, T4, T5, T6>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input7<T1, T2, T3, T4, T5, T6, T7>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
) {
  internal fun gather(): Flow<Tuple7th<T1, T2, T3, T4, T5, T6, T7>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input8<T1, T2, T3, T4, T5, T6, T7, T8>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
) {
  internal fun gather(): Flow<Tuple8th<T1, T2, T3, T4, T5, T6, T7, T8>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input9<T1, T2, T3, T4, T5, T6, T7, T8, T9>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
) {
  internal fun gather(): Flow<Tuple9th<T1, T2, T3, T4, T5, T6, T7, T8, T9>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
) {
  internal fun gather(): Flow<Tuple10th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
) {
  internal fun gather(): Flow<Tuple11th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
) {
  internal fun gather(): Flow<Tuple12th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
) {
  internal fun gather(): Flow<Tuple13th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
) {
  internal fun gather():
      Flow<Tuple14th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
) {
  internal fun gather():
      Flow<Tuple15th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
) {
  internal fun gather():
      Flow<Tuple16th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
) {
  internal fun gather():
      Flow<Tuple17th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17,
    T18>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
) {
  internal fun gather():
      Flow<Tuple18th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>>
      =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17,
    T18, T19>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
  public val inFlow19: InFlow<T19>,
) {
  internal fun gather():
      Flow<Tuple19th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>>
      =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).addInput(inFlow19).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
      `19th`: T19,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
      `19th`: T19,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

public class Input20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17,
    T18, T19, T20>(
  public val inFlow1: InFlow<T1>,
  public val inFlow2: InFlow<T2>,
  public val inFlow3: InFlow<T3>,
  public val inFlow4: InFlow<T4>,
  public val inFlow5: InFlow<T5>,
  public val inFlow6: InFlow<T6>,
  public val inFlow7: InFlow<T7>,
  public val inFlow8: InFlow<T8>,
  public val inFlow9: InFlow<T9>,
  public val inFlow10: InFlow<T10>,
  public val inFlow11: InFlow<T11>,
  public val inFlow12: InFlow<T12>,
  public val inFlow13: InFlow<T13>,
  public val inFlow14: InFlow<T14>,
  public val inFlow15: InFlow<T15>,
  public val inFlow16: InFlow<T16>,
  public val inFlow17: InFlow<T17>,
  public val inFlow18: InFlow<T18>,
  public val inFlow19: InFlow<T19>,
  public val inFlow20: InFlow<T20>,
) {
  internal fun gather():
      Flow<Tuple20th<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>>
      =
      InputsBuilderEmpty().addInput(inFlow1).addInput(inFlow2).addInput(inFlow3).addInput(inFlow4).addInput(inFlow5).addInput(inFlow6).addInput(inFlow7).addInput(inFlow8).addInput(inFlow9).addInput(inFlow10).addInput(inFlow11).addInput(inFlow12).addInput(inFlow13).addInput(inFlow14).addInput(inFlow15).addInput(inFlow16).addInput(inFlow17).addInput(inFlow18).addInput(inFlow19).addInput(inFlow20).build()

  public fun <R> transform(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
      `19th`: T19,
      `20th`: T20,
    ) -> R,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
          to.emit(it)
      }
    }

  public fun <R> transformFilter(
    scope: CoroutineScope,
    to: FlowCollector<R>,
    op: (
      `1st`: T1,
      `2nd`: T2,
      `3rd`: T3,
      `4th`: T4,
      `5th`: T5,
      `6th`: T6,
      `7th`: T7,
      `8th`: T8,
      `9th`: T9,
      `10th`: T10,
      `11th`: T11,
      `12th`: T12,
      `13th`: T13,
      `14th`: T14,
      `15th`: T15,
      `16th`: T16,
      `17th`: T17,
      `18th`: T18,
      `19th`: T19,
      `20th`: T20,
    ) -> PossibleResult<R>,
  ): Job = scope.launch {
      gather().map {
        it.throughGen(op)
      }.collect {
        if (it.shouldEmit) {
          to.emit(it.result)
        }
      }
    }
}

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(2, false)
 */
public fun <T1, T2, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  transform: (t1: T1, t2: T2) -> R,
  `out`: MutableSharedFlow<R>,
) = Input2(inFlow1, inFlow2).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(2, true)
 */
public fun <T1, T2, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  transform: (t1: T1, t2: T2) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input2(inFlow1, inFlow2).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(3, false)
 */
public fun <T1, T2, T3, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input3(inFlow1, inFlow2, inFlow3).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(3, true)
 */
public fun <T1, T2, T3, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input3(inFlow1, inFlow2, inFlow3).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(4, false)
 */
public fun <T1, T2, T3, T4, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input4(inFlow1, inFlow2, inFlow3, inFlow4).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(4, true)
 */
public fun <T1, T2, T3, T4, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input4(inFlow1, inFlow2, inFlow3, inFlow4).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(5, false)
 */
public fun <T1, T2, T3, T4, T5, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input5(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(5, true)
 */
public fun <T1, T2, T3, T4, T5, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input5(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(6, false)
 */
public fun <T1, T2, T3, T4, T5, T6, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input6(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(6, true)
 */
public fun <T1, T2, T3, T4, T5, T6, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input6(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6).transformFilter(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(7, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input7(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7).transform(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(7, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input7(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7).transformFilter(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(8, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input8(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8).transform(this,
    out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(8, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input8(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7,
    inFlow8).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(9, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input9(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
    inFlow9).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(9, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input9(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8,
    inFlow9).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(10, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input10(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(10, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input10(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(11, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input11(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(11, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input11(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(12, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input12(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(12, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input12(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(13, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input13(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(13, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input13(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(14, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input14(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(14, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input14(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(15, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input15(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(15, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input15(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15).transformFilter(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(16, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R>
    CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input16(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16).transform(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(16, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R>
    CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input16(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16).transformFilter(this, out,
    transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(17, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R>
    CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input17(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17).transform(this,
    out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(17, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R>
    CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input17(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16,
    inFlow17).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(18, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R>
    CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input18(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17,
    inFlow18).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(18, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R>
    CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input18(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17,
    inFlow18).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(19, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R>
    CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  inFlow19: InFlow<T19>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
    t19: T19,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input19(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17, inFlow18,
    inFlow19).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(19, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R>
    CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  inFlow19: InFlow<T19>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
    t19: T19,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input19(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17, inFlow18,
    inFlow19).transformFilter(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(20, false)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19,
    T20, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  inFlow19: InFlow<T19>,
  inFlow20: InFlow<T20>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
    t19: T19,
    t20: T20,
  ) -> R,
  `out`: MutableSharedFlow<R>,
) = Input20(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17, inFlow18,
    inFlow19, inFlow20).transform(this, out, transform)

/**
 * AUTOGEN(KotlinPoet) generateCoroutineOpNFn(20, true)
 */
public fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19,
    T20, R> CoroutineScope.opp(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  inFlow3: InFlow<T3>,
  inFlow4: InFlow<T4>,
  inFlow5: InFlow<T5>,
  inFlow6: InFlow<T6>,
  inFlow7: InFlow<T7>,
  inFlow8: InFlow<T8>,
  inFlow9: InFlow<T9>,
  inFlow10: InFlow<T10>,
  inFlow11: InFlow<T11>,
  inFlow12: InFlow<T12>,
  inFlow13: InFlow<T13>,
  inFlow14: InFlow<T14>,
  inFlow15: InFlow<T15>,
  inFlow16: InFlow<T16>,
  inFlow17: InFlow<T17>,
  inFlow18: InFlow<T18>,
  inFlow19: InFlow<T19>,
  inFlow20: InFlow<T20>,
  transform: (
    t1: T1,
    t2: T2,
    t3: T3,
    t4: T4,
    t5: T5,
    t6: T6,
    t7: T7,
    t8: T8,
    t9: T9,
    t10: T10,
    t11: T11,
    t12: T12,
    t13: T13,
    t14: T14,
    t15: T15,
    t16: T16,
    t17: T17,
    t18: T18,
    t19: T19,
    t20: T20,
  ) -> PossibleResult<R>,
  `out`: MutableSharedFlow<R>,
) = Input20(inFlow1, inFlow2, inFlow3, inFlow4, inFlow5, inFlow6, inFlow7, inFlow8, inFlow9,
    inFlow10, inFlow11, inFlow12, inFlow13, inFlow14, inFlow15, inFlow16, inFlow17, inFlow18,
    inFlow19, inFlow20).transformFilter(this, out, transform)
