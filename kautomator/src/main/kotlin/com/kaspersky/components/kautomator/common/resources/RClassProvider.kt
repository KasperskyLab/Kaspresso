package com.kaspersky.components.kautomator.common.resources

internal object RClassProvider {
    private val classNameToClass = mutableMapOf<String, Class<*>>()

    @Synchronized
    fun provideClass(className: String): Class<*> {
        var rClass = classNameToClass[className]
        if (rClass == null) {
            rClass = Class.forName(className)
            classNameToClass[className] = rClass
        }

        return rClass!!
    }
}