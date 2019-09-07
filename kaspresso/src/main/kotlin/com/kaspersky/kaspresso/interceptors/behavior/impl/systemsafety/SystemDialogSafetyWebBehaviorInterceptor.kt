package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyWebBehaviorInterceptor(
    params: SystemDialogSafetyParams,
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(params, logger) {

    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = passSystemDialogs(action)
}