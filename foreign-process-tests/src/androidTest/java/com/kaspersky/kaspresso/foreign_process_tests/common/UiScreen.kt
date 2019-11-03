package com.kaspersky.kaspresso.foreign_process_tests.common

@Suppress("UNCHECKED_CAST")
open class UiScreen<out T : UiScreen<T>> {

    companion object {
        const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspressample"
    }

    operator fun invoke(function: T.() -> Unit) {
        function.invoke(this as T)
    }
}