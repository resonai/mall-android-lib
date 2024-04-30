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

var MAX_INPUTS = 20
var MIN_INPUTS = 2
internal const val PACKAGE_NAME = "com.resonai.mall"

fun main() {
    generatePoetry(projectPath = "app/src/main/java")
}

fun generatePoetry(projectPath: String) {
    val file = FileSpec.builder(PACKAGE_NAME, "Poetry")
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
        file.addFunction(generateCoroutineOpNFn(n))
        file.addFunction(generateCoroutineOpNFn(n, true))
    }

    file.build().writeTo(File(projectPath))
}