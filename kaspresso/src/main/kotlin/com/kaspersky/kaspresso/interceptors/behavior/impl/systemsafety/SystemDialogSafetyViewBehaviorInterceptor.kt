package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyViewBehaviorInterceptor(
    params: SystemDialogSafetyParams,
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(params, logger) {

    override fun <R> intercept(interaction: ViewInteraction, action: () -> R): R = passSystemDialogs(action = action)
}