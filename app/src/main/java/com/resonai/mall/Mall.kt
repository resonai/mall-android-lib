package com.resonai.mall

import com.resonai.mall.GeneratePoetry.generateCombineTupleNFn
import com.resonai.mall.GeneratePoetry.generateCombinerTupleNFn
import com.resonai.mall.GeneratePoetry.generateCoroutineOpNFn
import com.resonai.mall.GeneratePoetry.generateInputsBuilderNClass
import com.resonai.mall.GeneratePoetry.generateInputsNClass
import com.resonai.mall.GeneratePoetry.generateTupleNFnThrough
import com.resonai.mall.GenerateTuples.generateTupleClass
import com.squareup.kotlinpoet.FileSpec
import java.io.File

internal const val PACKAGE_NAME = "com.resonai.mall"
internal var maxInputs = 20
private var minInputs = 2

fun main(args: Array<String>) {
    val inputs = if (args.isNotEmpty()) args[0].toInt() else 0
    maxInputs = inputs
    generatePoetry()
}

private fun generatePoetry() {
    val file = FileSpec.builder(PACKAGE_NAME, "Poetry")
        .addImport("kotlinx.coroutines.flow", "combine", "map", "zip")
        .addImport("kotlinx.coroutines", "launch")

    for (n in minInputs..maxInputs) {
        file.addType(generateTupleClass(n))
    }

    for (n in minInputs..maxInputs) {
        file.addFunction(generateCombineTupleNFn(n))
    }

    for (n in minInputs..maxInputs) {
        file.addFunction(generateCombinerTupleNFn(n, "zip"))
    }

    for (n in minInputs..maxInputs) {
        file.addFunction(generateTupleNFnThrough(n))
    }

    for (n in minInputs..maxInputs) {
        file.addType(generateInputsBuilderNClass(n, maxInputs))
    }

    for (n in minInputs..maxInputs) {
        file.addType(generateInputsNClass(n))
    }

    for (n in minInputs..maxInputs) {
        file.addFunction(generateCoroutineOpNFn(n))
        file.addFunction(generateCoroutineOpNFn(n, true))
    }

    file.build().writeTo(File("app/src/main/java"))
}