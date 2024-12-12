package com.kaspresso.components.pageobjectcodegen

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
