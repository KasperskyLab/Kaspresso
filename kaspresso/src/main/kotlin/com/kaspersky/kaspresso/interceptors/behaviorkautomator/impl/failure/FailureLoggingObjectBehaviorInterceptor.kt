package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ObjectBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class FailureLoggingObjectBehaviorInterceptor(
    logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)
}