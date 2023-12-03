package com.kaspresso.components.pageobjectcodegen

import com.google.common.io.Resources.getResource
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File
import java.lang.Runtime

@RunWith(Parameterized::class)
class CodeGenTest(
    private val inputPath: String,
    private val outputDirectory: String,
    private val className: String,
    private val resultFile: String,
) {

    @Test
    fun checkCodeGen() {
        val jarFile = File(getResource("createKtfromDump.jar").toURI())
        val inputFile = File(getResource(inputPath).toURI())
        Runtime.getRuntime().exec("java -jar $jarFile $inputFile $className $outputDirectory")
        Thread.sleep(15000)
        val actualFile = File("$outputDirectory/$className.kt")
        val expectedFile = File(getResource("$resultFile.txt").toURI())
        assertThat(actualFile).hasSameContentAs(expectedFile)
    }
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("source1.xml", "build/generated/res/com/kaspresso/components/pageobjectcodegen", "TestClass1", "Result1"),
            )
        }
    }
}
