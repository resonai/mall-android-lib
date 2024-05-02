package com.resonai.mall

import com.resonai.mall.GeneratePoetry.getOrdinal
import com.resonai.mall.Mall.MAX_INPUTS
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

internal object GenerateTuples {
    fun tupleTypeForN(
        n: Int, typeArguments: List<TypeName>
    ): ParameterizedTypeName {
        require(n in 2..MAX_INPUTS) {
            "Tuple size must be between 2 and $MAX_INPUTS"
        }

        val className = ClassName(PACKAGE_NAME, "Tuple${getOrdinal(n)}")
        return className.parameterizedBy(typeArguments)
    }

    fun generateTupleClass(n: Int): TypeSpec {
        val className = "Tuple${getOrdinal(n)}"
        val classBuilder = TypeSpec.classBuilder(className)
            .addModifiers(KModifier.DATA)

        val constructorBuilder = FunSpec.constructorBuilder()

        for (i in 1..n) {
            val typeVariableName = "Element${getOrdinal(i)}"
            classBuilder.addTypeVariable(
                TypeVariableName(
                    typeVariableName,
                    variance = KModifier.OUT
                )
            )

            val element = ParameterSpec.builder(
                "element${getOrdinal(i)}",
                TypeVariableName(typeVariableName)
            )
                .build()

            constructorBuilder.addParameter(element)

            classBuilder.addProperty(
                PropertySpec.builder(element.name, element.type)
                    .initializer(element.name)
                    .build()
            )
        }

        classBuilder.primaryConstructor(
            constructorBuilder.build()
        )

        return classBuilder.build()
    }
}
