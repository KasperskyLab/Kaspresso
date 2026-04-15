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

class PageObjectGenerator(elements: List<BaseView>, filePackage: String, private val className: String) :
    KotlinCodeGenerator(elements, filePackage) {
    override fun generate(writer: TextWriter) {
        super.generate(writer)
        with(writer) {
            codeBlock("object $className : KScreen<$className>()") {
                append(LAYOUT)
                append(VIEWCLASS, 2)
                createElements(elements).forEach {
                    append(it)
                }
                nextLine()
                elements.forEach { view ->
                    if (view is RecyclerView) {
                        for (i in 0 until view.childView.size) {
                            codeBlock(
                                "class ${view.childClassNames[i]}(matcher: Matcher<View>) : KRecyclerItem<${view.childClassNames[i]}>(matcher)",
                                countOfLinesAfterBegin = 1,
                                countOfLinesAfterEnd = 2,
                            ) {
                                createElements(view.childView.elementAt(i)).forEach {
                                    append(it, countOfLinesAfterText = 0, countOfLinesBeforeText = 1)
                                }
                            }
                        }
                    }
                }
                codeBlock("override fun BaseTestContext.waitForScreen()", countOfLinesAfterBegin = 1) {
                    append(TODO, 0)
                }
            }
        }
    }

    private fun createElements(screenElements: List<BaseView>): List<String> {
        return screenElements.map { it.toKaspressoExpression() }
    }

    companion object Constants {
        private const val LAYOUT = "override val layoutId: Int? = TODO(\"Need To Implement\")"
        private const val VIEWCLASS = "override val viewClass: Class<*>? = TODO(\"Need To Implement\")"
        private const val TODO = "TODO(\"Need To Implement\")"
    }
}
