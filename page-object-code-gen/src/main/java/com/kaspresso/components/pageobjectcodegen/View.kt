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

data class View(
    override val resourceId: String,
    override val viewType: ViewType,
    override val packages: String,
) : BaseView {

    override fun toKaspressoExpression(): String {
        return "val ${resourceId.toCamelCase()} = K$viewType { withId(R.id.$resourceId) }"
    }
}

data class RecyclerView(
    override val resourceId: String,
    override val viewType: ViewType,
    override val packages: String,
    val childView: Set<List<BaseView>>,
) : BaseView {

    val childClassNames = List(childView.size) { if (it == 0) "RecyclerViewItem" else "RecyclerViewItem$it" }

    override fun toKaspressoExpression(): String {
        return """val ${resourceId.toCamelCase()} = KRecyclerView(
        builder = { withId(R.id.$resourceId) },
        itemTypeBuilder = { ${childClassNames.joinToString(separator = ",\n" + "\t".repeat(AMOUNT_OF_TABS)) { "itemType(::$it)" }} },
    )"""
    }

    companion object {
        private const val AMOUNT_OF_TABS = 7
    }
}

interface BaseView {

    val resourceId: String
    val viewType: ViewType
    val packages: String
    fun toKaspressoExpression(): String

    fun String.toCamelCase() = replace("_[a-z]".toRegex()) { it.value.last().uppercase() }
}
