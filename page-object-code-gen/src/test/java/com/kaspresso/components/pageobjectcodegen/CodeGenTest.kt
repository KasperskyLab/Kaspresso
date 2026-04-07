package com.kaspresso.components.pageobjectcodegen

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File
import java.lang.Runtime

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
