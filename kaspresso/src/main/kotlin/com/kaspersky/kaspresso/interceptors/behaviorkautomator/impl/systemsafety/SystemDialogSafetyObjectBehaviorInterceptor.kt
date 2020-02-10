package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

/**
 * The implementation of [ObjectBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class SystemDialogSafetyObjectBehaviorInterceptor(
    logger: UiTestLogger,
    uiDevice: UiDevice,
    adbServer: AdbServer
) : ObjectBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice, adbServer) {

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = passSystemDialogs(activity)

    /**
     * Wraps the given [activity] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = passSystemDialogs(activity)
}