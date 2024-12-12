package com.kaspresso.components.pageobjectcodegen

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

enum class ViewType(val androidName: String) {
    TextView("android.widget.TextView") {
        override fun getClass(): List<String> {
            return listOf("import ${KTextView::class.qualifiedName}")
        }
    },
    ImageView("android.widget.ImageView") {
        override fun getClass(): List<String> {
            return listOf("import ${KImageView::class.qualifiedName}")
        }
    },
    Button("android.widget.Button") {
        override fun getClass(): List<String> {
            return listOf("import ${KButton::class.qualifiedName}")
        }
    },
    EditText("android.widget.EditText") {
        override fun getClass(): List<String> {
            return listOf("import ${KEditText::class.qualifiedName}")
        }
    },
    RecyclerView("androidx.recyclerview.widget.RecyclerView") {
        override fun getClass(): List<String> {
            return listOf("import ${KRecyclerItem::class.qualifiedName}", "import ${KRecyclerView::class.qualifiedName}")
        }
    }, ;
    abstract fun getClass(): List<String>
    companion object {
        val elementsWithChild = listOf(RecyclerView.androidName)
        val collectableElements = ViewType.values().map { it.androidName }.filter { it !in elementsWithChild }
    }
}
