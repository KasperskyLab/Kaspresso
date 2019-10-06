package com.kaspersky.kaspresso.compose

import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.internal.exceptions.ThrowableWithInteraction
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException

/**
 * Invokes the given list of [actions] and succeeds if at least one of them succeeds.
 *
 * @param actions the list of actions to invoke.
 * @param logger the logger
 */
internal fun invokeComposed(
    actions: List<() -> Unit>,
    logger: UiTestLogger,
    elements: List<Interceptable<*, *, *>>? = null
) {
    logger.i("Composed action started.")
    val cachedErrors: MutableList<Throwable> = mutableListOf()

    actions.forEachIndexed { i: Int, action: () -> Unit ->
        try {
            action.invoke()
            logger.i("Composed action succeeded.")
            return
        } catch (error: Throwable) {
            cachedErrors += elements?.let { ThrowableWithInteraction(error, it[i].view.interaction as Any) } ?: error
            logger.i("One part of composed action failed.")
        }
    }

    logger.i("Composed action totally failed.")
    throw ExtCompositeException(cachedErrors)
}