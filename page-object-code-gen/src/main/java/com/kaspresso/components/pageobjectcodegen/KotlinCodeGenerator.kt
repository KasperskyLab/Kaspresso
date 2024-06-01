package com.kaspresso.components.pageobjectcodegen

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
