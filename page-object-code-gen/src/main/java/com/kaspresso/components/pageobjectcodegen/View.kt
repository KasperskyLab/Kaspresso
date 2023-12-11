package com.kaspresso.components.pageobjectcodegen

data class View(
    override val resourceId: String,
    override val viewType: String,
    override val packages: String,
) : BaseView {

    override fun toKaspressoExpression(): String {
        return "val ${resourceId.toCamelCase()} = K$viewType { withId(R.id.$resourceId) }"
    }
}

data class RecyclerView(
    override val resourceId: String,
    override val viewType: String = "RecyclerView",
    override val packages: String,
    val childResourceId: Map<String, String>,
) : BaseView {

    override fun toKaspressoExpression(): String {
        return ""
    }
}

interface BaseView {

    val resourceId: String
    val viewType: String
    val packages: String
    fun toKaspressoExpression(): String

    fun String.toCamelCase() = replace("_[a-z]".toRegex()) { it.value.last().uppercase() }
}
