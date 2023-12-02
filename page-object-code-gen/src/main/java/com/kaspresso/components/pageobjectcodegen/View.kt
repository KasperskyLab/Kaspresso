package com.kaspresso.components.pageobjectcodegen

data class View(val resourceId: String, val viewType: String, val packages: String) {

    fun toKaspressoExpression(): String {
        return "val ${resourceId.toCamelCase()} = K$viewType{ withId(R.id.$resourceId) }"
    }

    private fun String.toCamelCase() = split('_', '-', ' ').joinToString("") { it.uppercase() }
}
