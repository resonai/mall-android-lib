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

internal var PACKAGE_NAME = "com.resonai.mall"

object Mall {

    internal var MAX_INPUTS = 20
    private var minInputs = 2

    fun generatePoetry(
        maxInputs: Int = 20,
        packageName: String = "com.resonai.mall",
        filePath: String = "app/src/main/java"
    ) {
        PACKAGE_NAME = packageName
        MAX_INPUTS = maxInputs
        val file = FileSpec.builder(packageName, "Poetry")
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

        file.build().writeTo(File(filePath))
    }
}