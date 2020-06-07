package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.ViewInteraction
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

/**
 * The implementation of [ViewBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [ViewInteraction.perform] and [ViewInteraction.check] calls.
 */
class SystemDialogSafetyViewBehaviorInterceptor(
    logger: UiTestLogger,
    uiDevice: UiDevice,
    adbServer: AdbServer
) : ViewBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice, adbServer) {

    /**
     * Wraps the given [action] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [ViewInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = passSystemDialogs(action)
}
