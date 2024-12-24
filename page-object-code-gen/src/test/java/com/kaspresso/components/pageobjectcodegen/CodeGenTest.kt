package com.kaspresso.components.pageobjectcodegen

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
        val jarFile = File("../artifacts/page-object-code-gen.jar")
        val inputFile = File("src/test/resources/$inputPath")
        Runtime.getRuntime().exec("java -jar $jarFile $inputFile $className $outputDirectory")
        Thread.sleep(15000)
        val actualFile = File("$outputDirectory/$className.kt")
        val expectedFile1 = File("src/test/resources/$resultFile.txt")
        assertThat(actualFile).hasSameContentAs(expectedFile1)
    }
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf("source1.xml", "build/generated/res/com/kaspresso/components/pageobjectcodegen", "TestClass1", "Result1"),
                arrayOf("source_recycler_view.xml", "build/generated/res/com/kaspresso/components/pageobjectcodegen", "RecyclerView", "ResultRecyclerView"),
                arrayOf("source2.xml", "build/generated/res/com/kaspresso/components/pageobjectcodegen", "TestClass2", "Result2"),
                arrayOf("source3.xml", "build/generated/res/com/kaspresso/components/pageobjectcodegen", "TestClass3", "Result3"),
            )
        }
    }
}
