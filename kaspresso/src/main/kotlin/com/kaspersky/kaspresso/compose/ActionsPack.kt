package com.kaspersky.kaspresso.compose

import java.lang.IllegalArgumentException

class ActionsPack<T> {

    private val actions: MutableList<T.() -> Unit> = arrayListOf()

    fun or(action: T.() -> Unit) {
        actions += action
    }

    internal fun build(): MutableList<T.() -> Unit> {
        if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actions
    }
}