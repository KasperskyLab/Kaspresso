package com.kaspresso.components.pageobjectcodegen

import io.github.kakaocup.kakao.text.KTextView

enum class ViewType {
    TextView {
        override fun getClass(): List<String> {
            return listOf(KTextView::class.qualifiedName!!)
        }
    },
    ImageView {
        override fun getClass(): List<String> {
            // return KImageView::class.qualifiedName!!
            return listOf("import io.github.kakaocup.kakao.image.KImageView")
        }
    },
    Button {
        override fun getClass(): List<String> {
            return listOf("import io.github.kakaocup.kakao.text.KButton")
        }
    },
    RecyclerView {
        override fun getClass(): List<String> {
            return listOf("import io.github.kakaocup.kakao.recycler.KRecyclerItem", "import io.github.kakaocup.kakao.recycler.KRecyclerView")
        }
    }, ;
    abstract fun getClass(): List<String>
    companion object {
        val collectableElements = listOf("android.widget.Button", "android.widget.TextView", "android.widget.ImageView")
        val elementsWithChild = listOf("androidx.recyclerview.widget.RecyclerView")
    }
}
