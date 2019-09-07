package com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety

import androidx.test.espresso.DataInteraction
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider
import com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl

class SystemDialogSafetyDataBehaviorInterceptor(
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : DataBehaviorInterceptor,
    SystemDialogSafetyProvider by SystemDialogSafetyProviderImpl(logger, uiDevice) {

    override fun <R> intercept(interaction: DataInteraction, action: () -> R): R = passSystemDialogs(action = action)
}