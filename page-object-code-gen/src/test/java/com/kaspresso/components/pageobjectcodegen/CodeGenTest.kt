package com.kaspresso.components.pageobjectcodegen

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File
import java.lang.Runtime
import java.nio.file.Files

class CodeGenTest {

    @ParameterizedTest(name = "Check code gen. Input: {0}. Output: {2}")
    @CsvSource(value = ["source1.xml, page-object-code-gen/src/test/java/com/kaspresso/components/pageobjectcodegen, TestClass1"])
    fun checkCodeGen(inputPath: String, outputDirectory: String, className: String) {
        Runtime.getRuntime().exec("java -jar createKtFromDump.jar $inputPath $className $outputDirectory")
        Assertions.assertEquals(
            Files.readAllLines(File("Result1.kt").toPath()),
            Files.readAllLines(File("$outputDirectory/$className.kt").toPath()),
        )
    }
}
