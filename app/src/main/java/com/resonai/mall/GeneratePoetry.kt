package com.resonai.mall

import com.squareup.kotlinpoet.ANY
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.asClassName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow

fun getOrdinal(n: Int): String {
    val suffixes =
        listOf("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")
    return when (n) {
        11, 12, 13 -> n.toString() + "th"
        else -> n.toString() + suffixes[n % 10]
    }
}

private fun flowTupleNFnBuilder(n: Int, name: String):
        Pair<FunSpec.Builder, ParameterizedTypeName> {
    // n > 1
    assert(n > 1)
    val types = (1..n).map { TypeVariableName("T$it") }
    val params = (1..n).map {
        ParameterSpec.builder(
            "flow$it",
            Flow::class.asClassName().parameterizedBy(types[it - 1])
        ).build()
    }

    val tupleType = tupleTypeForN(n, types)
    return Pair(
        FunSpec.builder("${name}TupleGen")
            .addTypeVariables(types)
            .returns(Flow::class.asClassName().parameterizedBy(tupleType))
            .addParameters(params), tupleType
    )
}

fun generateCombineTupleNFn(n: Int): FunSpec {
    if (n > 5) {
        return generateCombinerTupleNFn(n, "combine")
    }

    val fnBuilderPair = flowTupleNFnBuilder(n, "combine")
    fnBuilderPair.first
        .addKdoc("AUTOGEN(KotlinPoet) generateCombineTupleNFn(%L)", n)
        .addStatement(
            String.format(
                "return %s(%s) { %s -> %s(%s) }",
                "combine",
                (1..n).map { "flow$it" }.joinToString(),
                (1..n).map { "t$it" }.joinToString(),
                fnBuilderPair.second.toString(),
                (1..n).map { "t$it" }.joinToString()
            ),
        )
    return fnBuilderPair.first.build()
}

private fun generateCombinerTupleNFn(n: Int, combiner: String): FunSpec {
    assert(n > 1)
    val fnBuilderPair = flowTupleNFnBuilder(n, combiner)
    when {
        n == 2 -> fnBuilderPair.first
            .addStatement("return flow1.$combiner(flow2) { z1, z2 -> Tuple2nd(z1, z2) }")

        n > 2 -> {
            fnBuilderPair.first
                .addStatement(
                    String.format(
                        "return ${combiner}TupleGen(%s).${combiner}(flow$n) { z, z2 -> %s(%s, z2)}",
                        (1 until n).joinToString { "flow$it" },
                        fnBuilderPair.second.toString(),
                        (1 until n).joinToString { "z.element${getOrdinal(it)}" }
                    )
                )
        }
    }
    return fnBuilderPair.first
        .addKdoc(
            "AUTOGEN(KotlinPoet) generateCombinerTupleNFn(%L, %L)",
            n, combiner
        )
        .build()
}

fun generateTupleNFnThrough(n: Int): FunSpec {
    // n > 1
    assert(n > 1)
    val types = (1..n).map { TypeVariableName("T$it") }
    val tupleType = tupleTypeForN(n, types)
    val rType = TypeVariableName("R")
    val params = (1..n).map {
        ParameterSpec.builder("p$it", types[it - 1]).build()
    }
    val lambdaT = LambdaTypeName.get(
        parameters = params,
        returnType = rType
    )

    val fnBuilder = FunSpec.builder("throughGen")
        .receiver(tupleType)
        .addKdoc("AUTOGEN(KotlinPoet) generateTupleNFnThrough(%L)", n)
        .addTypeVariables(types)
        .addTypeVariable(rType)
        .addParameter(ParameterSpec.builder("fn", lambdaT).build())
        .addStatement(
            "return fn(${
                (1..n).joinToString(", ") { "element${getOrdinal(it)}" }
            })"
        )
        .returns(rType)

    return fnBuilder.build()
}

/* Generates something like
public fun <T1, T2, R> CoroutineScope.op(
  inFlow1: InFlow<T1>,
  inFlow2: InFlow<T2>,
  transform: (t1: T1, t2: T2) -> R,
  `out`: MutableSharedFlow<R>,
) = Input2(inFlow1, inFlow2).transform(this, out, transform)
 */
fun generateCoroutineOpNFn(n: Int): List<FunSpec> {
    // n > 1
    assert(n > 1)
    val types = (1..n).map { TypeVariableName("T$it") }
    val rType = TypeVariableName("R")
    val transformParams = (1..n).map {
        ParameterSpec.builder("t$it", types[it - 1]).build()
    }
    val inFlowParams = (1..n).map {
        ParameterSpec.builder(
            "inFlow$it",
            InFlow::class.asClassName().parameterizedBy(types[it - 1])
        ).build()
    }
    val rToRType = LambdaTypeName.get(
        parameters = listOf(ParameterSpec.builder("", rType).build()),
        returnType = rType
    )

    val possibleRToRType = LambdaTypeName.get(
        parameters = listOf(ParameterSpec.builder("", rType).build()),
        returnType = PossibleResult::class.asClassName().parameterizedBy(rType)
    )

    return mapOf(
        "op" to Pair(
            "transform", LambdaTypeName.get(
                parameters = transformParams,
                returnType = rType
            )
        ),

        "opp" to Pair(
            "transformFilter", LambdaTypeName.get(
                parameters = transformParams,
                returnType = PossibleResult::class.asClassName().parameterizedBy(rType)
            )
        ),

        "up" to Pair(
            "transformUpdate", LambdaTypeName.get(
                parameters = transformParams,
                returnType = rToRType
            )
        ),

        "upp" to Pair(
            "transformUpdateFilter", LambdaTypeName.get(
                parameters = transformParams,
                returnType = possibleRToRType
            )
        ),
    ).map { (funName, transformNameAndOpParams) ->
        FunSpec.builder(funName)
            .receiver(CoroutineScope::class)
            .addKdoc(
                "AUTOGEN(KotlinPoet) generateCoroutineOpNFn(%L)",
                n
            )
            .addTypeVariables(types)
            .addTypeVariable(rType)
            .addParameters(inFlowParams)
            .addParameter(
                ParameterSpec.builder("transform", transformNameAndOpParams.second).build()
            )
            .addParameter(
                ParameterSpec.builder(
                    "out",
                    MutableSharedFlow::class.asClassName().parameterizedBy(rType)
                ).build()
            )
            .addStatement(
                "return Input$n(${(1..n).joinToString(", ") { "inFlow$it" }})" +
                        ".${transformNameAndOpParams.first}(this, out, transform)"
            ).build()
    }
}

fun generateInputsBuilderNClass(n: Int, maxGenerated: Int = 8): TypeSpec {
    assert(n > 1)
    val types = (1..n).map { TypeVariableName("T$it") }
    val tupleType = tupleTypeForN(n, types)

    val inFlowParams = (1..n).map {
        ParameterSpec.builder(
            "inFlow$it",
            InFlow::class.asClassName().parameterizedBy(types[it - 1])
        ).build()
    }
    val inFlowProperties = (1..n).map {
        PropertySpec.builder(
            "inFlow$it",
            InFlow::class.asClassName().parameterizedBy(types[it - 1])
        )
            .initializer("inFlow$it")
            .build()
    }
    val initWithFlows = FunSpec.constructorBuilder()
        .addParameters(inFlowParams)
        .build()

    // TODO(ShirL) This should be usable as WildcardTypeName.STAR but it's not
    val star = WildcardTypeName.producerOf(ANY.copy(nullable = true))
    val nextType = TypeVariableName("T${n + 1}")
    val nextInputs =
        if (n < maxGenerated) ClassName("com.resonai.mall", "InputsBuilder${n + 1}")
            .parameterizedBy(types)
            .plusParameter(nextType) else ClassName(
            "com.resonai.mall",
            "InputAddable"
        ).parameterizedBy(star)

    val addInputFn = FunSpec.builder("addInput")
        .addTypeVariable(nextType)
        .addParameter(
            ParameterSpec.builder(
                "inFlow",
                InFlow::class.asClassName().parameterizedBy(nextType)
            ).build()
        )
        .addModifiers(KModifier.OVERRIDE)
        .returns(nextInputs)
        .addStatement(
            if (n < maxGenerated) String.format(
                "return InputsBuilder${n + 1}(%s, inFlow)",
                (1..n).joinToString { "inFlow$it" }
            ) else "TODO(\"Too many inputs\")"
        )
        .build()

    val buildUnsafeFn = FunSpec.builder("buildUnsafe")
        .addModifiers(KModifier.PRIVATE)
        .returns(
            Flow::class.asClassName().parameterizedBy(
                List::class.asClassName().parameterizedBy(star)
            )
        )
        .addStatement(
            String.format(
                "return InputsBuilder()%s.build()",
                (1..n).joinToString("") { ".addInput(inFlow$it)" }
            )
        )
        .build()

    val buildSafeFn = FunSpec.builder("build")
        .addModifiers(KModifier.OVERRIDE)
        .returns(Flow::class.asClassName().parameterizedBy(tupleType))
        .addStatement(
            String.format(
                "return buildUnsafe().map {\n" +
                        "\t@Suppress(\"UNCHECKED_CAST\")\n" +
                        "\t%s(%s)\n" +
                        "}\n",
                tupleType.rawType,
                (1..n).joinToString { "it[${it - 1}] as T$it" }
            )
        )
        .build()

    val insBuilder = TypeSpec.classBuilder("InputsBuilder$n")
        .addKdoc("AUTOGEN(KotlinPoet) generateInputsBuilderNClass(%L)", n)
        .addTypeVariables(types)
        .addSuperinterface(
            InputAddable::class.asClassName().parameterizedBy(tupleType)
        )
        .primaryConstructor(initWithFlows)
        .addProperties(inFlowProperties)
        .addFunction(addInputFn)
        .addFunction(buildUnsafeFn)
        .addFunction(buildSafeFn)

    return insBuilder.build()
}

fun generateInputsNClass(n: Int): TypeSpec {
    assert(n > 1)
    val types = (1..n).map { TypeVariableName("T$it") }
    val tupleType = tupleTypeForN(n, types)

    val inFlowParams = (1..n).map {
        ParameterSpec.builder(
            "inFlow$it",
            InFlow::class.asClassName().parameterizedBy(types[it - 1])
        )
            .build()
    }
    val inFlowProperties = (1..n).map {
        PropertySpec.builder(
            "inFlow$it",
            InFlow::class.asClassName().parameterizedBy(types[it - 1])
        )
            .initializer("inFlow$it")
            .build()
    }
    val initWithFlows = FunSpec.constructorBuilder()
        .addParameters(inFlowParams)
        .build()

    val gatherFn = FunSpec.builder("gather")
        .addModifiers(KModifier.INTERNAL)
        .returns(
            Flow::class.asClassName().parameterizedBy(tupleType)
        )
        .addStatement(
            String.format(
                "return InputsBuilderEmpty()%s.build()",
                (1..n).joinToString("") { ".addInput(inFlow$it)" }
            )
        )
        .build()

    val builder = TypeSpec.classBuilder("Input$n")
        .addTypeVariables(types)
        .primaryConstructor(initWithFlows)
        .addProperties(inFlowProperties)
        .addFunction(gatherFn)

    val rType = TypeVariableName("R")
    val transformParams = (1..n).map {
        ParameterSpec.builder(getOrdinal(it), types[it - 1]).build()
    }
    val rToRType = LambdaTypeName.get(
        parameters =
        listOf(ParameterSpec.builder("", rType).build()), returnType = rType
    )

    val possibleRToRType = LambdaTypeName.get(
        parameters = listOf(ParameterSpec.builder("", rType).build()),
        returnType = PossibleResult::class.asClassName().parameterizedBy(rType)
    )

    val transformMethods: Map<String, Pair<String, LambdaTypeName>> = mapOf(
        "transform" to Pair(
            first = "        to.emit(it)\n",
            second = LambdaTypeName.get(parameters = transformParams, returnType = rType)
        ),

        "transformFilter" to Pair(
            first = "      if (it.shouldEmit) {\n        to.emit(it.result)\n      }\n",
            second = LambdaTypeName.get(
                parameters = transformParams,
                returnType = PossibleResult::class.asClassName().parameterizedBy(rType)
            )
        ),

        "transformUpdate" to Pair(
            first = "      to.update(it) \n",
            second = LambdaTypeName.get(parameters = transformParams, returnType = rToRType)
        ),

        "transformUpdateFilter" to Pair(
            first = "      to.possibleUpdate(it) \n",
            second = LambdaTypeName.get(
                parameters = transformParams,
                returnType = possibleRToRType
            )
        ),
    )

    transformMethods.map { (funName, collectBodyAndOpParams) ->
        FunSpec.builder(funName)
            .addTypeVariable(rType)
            .addParameter(
                ParameterSpec.builder(
                    "scope",
                    CoroutineScope::class.asClassName()
                ).build()
            )
            .addParameter(
                ParameterSpec.builder(
                    "to",
                    MutableSharedFlow::class.asClassName().parameterizedBy(rType)
                ).build()
            )
            .addParameter(ParameterSpec.builder("op", collectBodyAndOpParams.second).build())
            .returns(Job::class.asClassName())
            .addStatement(
                "return scope.launch {\n" +
                        "    gather().map {\n" +
                        "      it.throughGen(op)\n" +
                        "    }.collect {\n" +
                        // TODO(ShirL) We may be able to save ourselves some code by using
                        // the built-in transform function, which behaves like map+filter
                        // https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/transform.html
                        collectBodyAndOpParams.first +
                        "    }\n" +
                        "  }"
            )
            .build()
    }.forEach {
        builder
            .addFunction(it)
    }

    return builder
        .build()
}

var MAX_INPUTS = 20
var MIN_INPUTS = 2

fun main() {
    generateFile()
}

private fun generateFile() {
    val file = FileSpec.builder("com.resonai.mall", "")
        .addImport("kotlinx.coroutines.flow", "combine", "map", "zip")
        .addImport("kotlinx.coroutines", "launch")

    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addType(generateTupleClass(n))
    }

    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addFunction(generateCombineTupleNFn(n))
    }

    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addFunction(generateCombinerTupleNFn(n, "zip"))
    }
    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addFunction(generateTupleNFnThrough(n))
    }
    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addType(generateInputsBuilderNClass(n, MAX_INPUTS))
    }

    for (n in MIN_INPUTS..MAX_INPUTS) {
        file.addType(generateInputsNClass(n))
    }

    for (n in MIN_INPUTS..MAX_INPUTS) {
        generateCoroutineOpNFn(n).forEach { file.addFunction(it) }
    }

    file.build().writeTo(System.out)
}