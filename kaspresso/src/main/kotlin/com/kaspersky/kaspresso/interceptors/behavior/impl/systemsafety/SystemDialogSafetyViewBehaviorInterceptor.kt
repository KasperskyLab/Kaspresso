package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.ViewInteraction
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyViewBehaviorInterceptor(
    logger: UiTestLogger,
    uiDevice: UiDevice
) : ViewBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice) {

    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = passSystemDialogs(action)
}