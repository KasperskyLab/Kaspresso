package com.kaspersky.kaspresso.compose

import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.ExtCompositeException

/**
 * Invokes the given list of [actions] and succeeds if at least one of them succeeds.
 *
 * @param actions the list of actions to invoke.
 * @param logger the logger
 * @param failureLoggingProvider FailureLoggingProvider if exists
 * @param flakySafetyProvider FlakySafetyProvider if exists
 *
 * @return the order number of a succeed branch
 */
internal fun invokeComposed(
    actions: List<() -> Unit>,
    logger: UiTestLogger,
    failureLoggingProvider: FailureLoggingProvider? = null,
    flakySafetyProvider: FlakySafetyProvider? = null
): Int {

    val composeAction: () -> Int = {
        logger.i("Composed action started.")
        var successBranchIndex: Int? = null
        val cachedErrors: MutableList<Throwable> = mutableListOf()

        actions.forEachIndexed { index, action ->
            try {
                action.invoke()
                logger.i("Composed action #$index succeeded.")
                successBranchIndex = index
            } catch (error: Throwable) {
                cachedErrors += error
                logger.e("Composed action #$index failed. The reason is ${error.javaClass.simpleName}")
            }
        }

        logger.i("All composed actions totally failed.")
        successBranchIndex ?: throw ExtCompositeException(cachedErrors)
    }

    return failureLoggingProvider.withLoggingOnFailureIfNotNull {
        flakySafetyProvider.flakySafelyIfNotNull {
            composeAction
        }
    }.invoke()
}

/**
 * The function to call [FailureLoggingProvider.withLoggingOnFailure] with null-safety.
 *
 * @param action the action to invoke.
 *
 * @return the result of the [action] invocation.
 */
internal fun <T> FailureLoggingProvider?.withLoggingOnFailureIfNotNull(action: () -> T): T =
    if (this != null) withLoggingOnFailure(action) else action.invoke()

/**
 * The function to call [FlakySafetyProvider.flakySafely] with null-safety.
 *
 * @param action the action to invoke.
 *
 * @return the result of the [action] invocation.
 */
internal fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(action: () -> T): T =
    this?.flakySafely(action) ?: action.invoke()