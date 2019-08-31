package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

import com.kaspersky.kaspresso.configurator.Configurator

@JvmName("invokeComposedActions")
internal fun <T> invokeComposed(element: T, actions: List<T.() -> Unit>) {
    Configurator.logger.i("Composed action started.")
    var cachedError: Throwable? = null

    actions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(element)
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

@JvmName("invokeComposedComponents")
internal fun <T> invokeComposed(components: List<Component<T>>) {
    Configurator.logger.i("Composed action started.")
    var cachedError: Throwable? = null

    components.forEach { component: Component<T> ->
        try {
            component.action.invoke(component.element)
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

@JvmName("invokeComposedWebComponents")
internal fun <T> invokeComposed(webComponents: List<WebComponent<T>>) {
    Configurator.logger.i("Composed action started.")
    var cachedError: Throwable? = null

    webComponents.forEach { component: WebComponent<T> ->
        try {
            component.action.invoke(component.element)
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