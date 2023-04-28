package com.kaspersky.components.kautomator.common.resources

internal abstract class ResourceNameProvider {
    protected abstract val rClassName: String

    fun resolveResName(packageName: String, resId: Int): String {
        val rClass = RClassProvider.provideClass("$packageName.$rClassName")
        return getResourceNameById(rClass, resId)
    }

    private fun getResourceNameById(rClass: Class<*>, id: Int): String {
        return rClass.fields
            .first { it.getInt(it) == id }
            .name
    }
}