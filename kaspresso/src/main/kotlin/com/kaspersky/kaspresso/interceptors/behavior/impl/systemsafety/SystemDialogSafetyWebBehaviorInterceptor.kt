package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyParams
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider

class SystemDialogSafetyWebBehaviorInterceptor(
    override val params: SystemDialogSafetyParams,
    override val logger: UiTestLogger
) : WebBehaviorInterceptor, SystemDialogSafetyProvider {

    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R = passSystemDialogs(action = action)
}