package com.kaspresso.components.pageobjectcodegen

abstract class KotlinCodeGenerator(val elements: List<View>, val filePackage: String) : Generator {
    override fun generate(writer: TextWriter) {
        with(writer) {
            if (!filePackage.isEmpty()) {
                append("package $filePackage", 2)
            }
            createImports(elements).forEach {
                append(it)
            }
            nextLine()
        }
    }

    private fun createImports(screenElements: List<View>): Set<String> {
        val importsList = mutableSetOf("import com.screen.common.KScreen", "import ${screenElements.first().packages}.R")

        for (element in screenElements) {
            when (element.viewType) {
                "ImageView" -> importsList.add("import io.guthub.kakaocup.kakao.image.KImageView")
                "Button" -> importsList.add("import io.guthub.kakaocup.kakao.text.KButton")
                "TextView" -> importsList.add("import io.guthub.kakaocup.kakao.text.KTextView")
                "RecyclerView" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerView")
                "RecyclerItem" -> importsList.add("import io.guthub.kakaocup.kakao.recycler.KRecyclerItem")
                "EditText" -> importsList.add("import io.guthub.kakaocup.kakao.edit.KEditText")
            }
        }
        return importsList
    }
}
