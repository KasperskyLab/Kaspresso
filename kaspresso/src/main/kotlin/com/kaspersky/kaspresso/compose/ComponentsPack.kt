package com.kaspersky.kaspresso.compose

import java.lang.IllegalArgumentException

open class Component<T>(
    open val element: T,
    open val action: T.() -> Unit
)

class ComponentPack<T> {

    private val components: MutableList<Component<T>> = arrayListOf()

    fun or(element: T, action: T.() -> Unit) {
        components += Component(element, action)
    }

    internal fun build(): MutableList<Component<T>> {
        if (components.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return components
    }
}