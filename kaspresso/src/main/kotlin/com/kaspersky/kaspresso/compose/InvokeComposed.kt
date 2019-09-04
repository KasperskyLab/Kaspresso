package com.kaspersky.kaspresso.compose

internal fun <T> ComposeProvider.invokeComposed(element: T, actions: List<T.() -> Unit>) {
    configurator.libLogger.i("Composed action started.")
    var cachedError: Throwable? = null

    actions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(element)
            configurator.libLogger.i("Composed action successfully performed.")
            return
        } catch (error: Throwable) {
            cachedError = error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw cachedError!!
}

internal fun <T> WebComposeProvider.invokeComposed(webElement: T, webActions: List<T.() -> Unit>) {
    configurator.libLogger.i("Composed action started.")
    var cachedError: Throwable? = null

    webActions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(webElement)
            configurator.libLogger.i("Composed action successfully performed.")
            return
        } catch (error: Throwable) {
            cachedError = error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw cachedError!!
}

internal fun <T> ComposeProvider.invokeComposed(components: List<Component<T>>) {
    configurator.libLogger.i("Composed action started.")
    var cachedError: Throwable? = null

    components.forEach { component: Component<T> ->
        try {
            component.action.invoke(component.element)
            configurator.libLogger.i("Composed action successfully performed.")
            return
        } catch (error: Throwable) {
            cachedError = error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw cachedError!!
}

internal fun WebComposeProvider.invokeComposed(webComponents: List<WebComponent>) {
    configurator.libLogger.i("Composed action started.")
    var cachedError: Throwable? = null

    webComponents.forEach { component: WebComponent ->
        try {
            component.webAction.invoke(component.webElement)
            configurator.libLogger.i("Composed action successfully performed.")
            return
        } catch (error: Throwable) {
            cachedError = error
            configurator.libLogger.i("One part of composed action failed.")
        }
    }

    configurator.libLogger.i("Composed action totally failed.")
    throw cachedError!!
}