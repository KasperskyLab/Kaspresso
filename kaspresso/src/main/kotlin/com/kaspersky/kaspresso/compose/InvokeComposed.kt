package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException

/**
 * Invokes the given list of [actions] and succeeds if at least one of them succeeds.
 *
 * @param actions the list of actions to invoke.
 * @param logger the logger
 *
 * @return the order number of a succeed branch
 */
internal fun invokeComposed(actions: List<() -> Unit>, logger: UiTestLogger): Int {
    logger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    actions.forEachIndexed { index, action  ->
        try {
            action.invoke()
            logger.i("Composed action succeeded.")
            return index
        } catch (error: Throwable) {
            cachedErrors += error
            logger.i("One part of composed action failed.")
        }
    }

    logger.i("Composed action totally failed.")
    throw ExtCompositeException(cachedErrors)
}