package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.web.sugar.Web
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyWebBehaviorInterceptor(
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : WebBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice) {

    override fun <R> intercept(interaction: Web.WebInteraction<*>, action: () -> R): R = passSystemDialogs(action = action)
}