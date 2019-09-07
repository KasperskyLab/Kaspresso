package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.CompositeException

internal fun invokeComposed(actions: List<() -> Unit>, logger: UiTestLogger) {
    logger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    actions.forEach { action: () -> Unit ->
        try {
            action.invoke()
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