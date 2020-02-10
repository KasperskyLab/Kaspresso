package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.failure

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [DeviceBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [UiDeviceInteraction.perform] and [UiDeviceInteraction.check] calls.
 *
 * By default, this interceptor is not used in Kaspresso.
 * If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider] directly
 */
class FailureLoggingDeviceBehaviorInterceptor(
    logger: UiTestLogger
) : DeviceBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param assertion the intercepted [UiDeviceAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiDeviceInteraction,
        assertion: UiDeviceAssertion,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [UiDeviceInteraction].
     * @param action the intercepted [UiDeviceAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiDeviceInteraction,
        action: UiDeviceAction,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)
}