package com.kaspresso.components.pageobjectcodegen

abstract class KotlinCodeGenerator(val elements: List<BaseView>, val filePackage: String) : Generator {
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
        val importsList = mutableSetOf("import com.screen.common.KScreen", "import ${screenElements.first().packages}.R")

        for (element in screenElements) {
            when (element.viewType) {
                "ImageView" -> importsList.add("import io.guthub.kakaocup.kakao.image.KImageView")
                "Button" -> importsList.add("import io.guthub.kakaocup.kakao.text.KButton")
                "TextView" -> importsList.add("import io.guthub.kakaocup.kakao.text.KTextView")
                "EditText" -> importsList.add("import io.guthub.kakaocup.kakao.edit.KEditText")
            }
            if (element is RecyclerView) {
                importsList.addAll(mutableSetOf("import io.guthub.kakaocup.kakao.recycler.KRecyclerView", "import io.guthub.kakaocup.kakao.recycler.KRecyclerItem"))
                importsList.addAll(createImports(element.childView.flatten()))
            }
        }
        return importsList.sorted()
    }
}
