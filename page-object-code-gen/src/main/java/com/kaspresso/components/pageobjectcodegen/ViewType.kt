package com.kaspresso.components.pageobjectcodegen

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

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
