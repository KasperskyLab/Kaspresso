package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import android.support.test.espresso.web.sugar.Web
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

/**
 * The implementation of [WebBehaviorInterceptor] and [SystemDialogSafetyProvider] interfaces.
 * Provides system dialog safety functionality for [Web.WebInteraction.perform] and [Web.WebInteraction.check] calls.
 */
class SystemDialogSafetyWebBehaviorInterceptor(
    logger: UiTestLogger,
    uiDevice: UiDevice
) : WebBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice) {

    /**
     * Wraps the given [action] invocation with the system dialog safety.
     *
     * @param interaction the intercepted [Web.WebInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = passSystemDialogs(action)
}