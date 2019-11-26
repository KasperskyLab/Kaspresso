package com.kaspersky.components.uiautomator_dsl.screen

@Suppress("UNCHECKED_CAST")
open class UiScreen<out T : UiScreen<T>> {

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }
}
