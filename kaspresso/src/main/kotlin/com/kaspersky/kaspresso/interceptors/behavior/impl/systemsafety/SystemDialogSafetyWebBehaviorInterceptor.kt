package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.web.sugar.Web
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyWebBehaviorInterceptor(
    logger: UiTestLogger,
    uiDevice: UiDevice
) : WebBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice) {

    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = passSystemDialogs(action)
}