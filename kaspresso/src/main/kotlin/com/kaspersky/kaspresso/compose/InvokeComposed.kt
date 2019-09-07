package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.CompositeException

internal fun <T> invokeComposed(element: T, actions: List<T.() -> Unit>, logger: UiTestLogger) {
    logger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    actions.forEach { action: T.() -> Unit ->
        try {
            action.invoke(element)
            logger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            logger.i("One part of composed action failed.")
        }
    }

    logger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}

internal fun <T> invokeComposed(components: List<Component<T>>, logger: UiTestLogger) {
    logger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    components.forEach { component: Component<T> ->
        try {
            component.action.invoke(component.element)
            logger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += error
            logger.i("One part of composed action failed.")
        }
    }

    logger.i("Composed action totally failed.")
    throw CompositeException(cachedErrors)
}