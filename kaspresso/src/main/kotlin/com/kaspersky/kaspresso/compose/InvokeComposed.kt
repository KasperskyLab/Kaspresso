package com.kaspersky.kaspresso.compose

import io.reactivex.exceptions.CompositeException

internal fun <T> ComposeProvider.invokeComposed(element: T, actions: List<T.() -> Unit>) {
    configurator.libLogger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    actions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(element)
            configurator.libLogger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}

internal fun <T> WebComposeProvider.invokeComposed(webElement: T, webActions: List<T.() -> Unit>) {
    configurator.libLogger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    webActions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(webElement)
            configurator.libLogger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}

internal fun <T> ComposeProvider.invokeComposed(components: List<Component<T>>) {
    configurator.libLogger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    components.forEach { component: Component<T> ->
        try {
            component.action.invoke(component.element)
            configurator.libLogger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}

internal fun WebComposeProvider.invokeComposed(webComponents: List<WebComponent>) {
    configurator.libLogger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    webComponents.forEach { component: WebComponent ->
        try {
            component.webAction.invoke(component.webElement)
            configurator.libLogger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}