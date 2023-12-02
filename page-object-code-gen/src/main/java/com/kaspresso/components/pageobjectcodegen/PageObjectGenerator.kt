package com.kaspresso.components.pageobjectcodegen

class PageObjectGenerator(elements: List<View>, filePackage: String, val className: String) :
    KotlinCodeGenerator(elements, filePackage) {
    override fun generate(writer: TextWriter) {
        super.generate(writer)
        with(writer) {
            codeBlock("object $className : KScreen<$className>()") {
                appendLine(LAYOUT)
                appendLine(VIEWCLASS)
                nextLine()
                createElements(elements).forEach {
                    appendLine(it)
                }
                nextLine()
                codeBlock("override fun BaseTestContext.waitForScreen()") {
                    append(TODO)
                }
                nextLine()
            }
        }
    }

    private fun createElements(screenElements: List<View>): List<String> {
        return screenElements.map { it.toKaspressoExpression() }
    }

    companion object Constants {
        private const val LAYOUT = "override val layoutId: Int? = TODO(\"Need To Implement\")"
        private const val VIEWCLASS = "override val viewClass: Class<*>? = TODO(\"Need To Implement\")"
        private const val TODO = "TODO(\"Need To Implement\")"
    }
}
