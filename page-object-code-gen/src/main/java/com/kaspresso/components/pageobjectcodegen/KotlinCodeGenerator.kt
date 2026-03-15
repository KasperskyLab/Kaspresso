package com.kaspresso.components.pageobjectcodegen

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

abstract class KotlinCodeGenerator(val elements: List<BaseView>, private val filePackage: String) : Generator {
    override fun generate(writer: TextWriter) {
        with(writer) {
            if (filePackage.isNotEmpty()) {
                append("package $filePackage", 2)
            }
            createImports(elements).forEach {
                append(it)
            }
            nextLine()
        }
    }

    private fun createImports(screenElements: List<BaseView>): List<String> {
        val importsList = mutableSetOf("import com.screens.common.KScreen", "import ${screenElements.first().packages}.R")

        for (element in screenElements) {
            importsList.addAll(element.viewType.getClass())
            if (element is RecyclerView) {
                importsList.addAll(createImports(element.childView.flatten()))
            }
        }
        return importsList.sorted()
    }
}
