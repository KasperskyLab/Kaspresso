package com.kaspersky.kaspresso.compose.pack

import java.lang.IllegalArgumentException

class ActionsPack<T>(
    private val element: T
) {
    private val actions: MutableList<() -> Unit> = arrayListOf()

    fun or(action: T.() -> Unit) {
        actions += { action.invoke(element) }
    }

    internal fun build(): List<() -> Unit> {
        if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actions
    }
}