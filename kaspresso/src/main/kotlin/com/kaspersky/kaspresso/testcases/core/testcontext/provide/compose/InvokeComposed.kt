package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

import com.kaspersky.kaspresso.configurator.Configurator

internal fun <T> invokeComposed(context: T, actions: List<T.() -> Unit>) {
    Configurator.logger.i("Composed action started.")
    var cachedError: Throwable? = null

    actions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(context)
            Configurator.logger.i("Composed action successfully performed.")
            return
        } catch (error: Throwable) {
            cachedError = error
            Configurator.logger.i("One part of composed action failed.")
        }
    }

    Configurator.logger.i("Composed action totally failed.")
    throw cachedError!!
}