package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.impl.logging

import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion
import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.DeviceWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class LoggingDeviceWatcherInterceptor(
    private val logger: UiTestLogger
) : DeviceWatcherInterceptor {

    override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion) {
        logger.i("Check=${assertion.type}(description={${assertion.description}} is executing on " +
                "global layout") // todo think about global layout
    }

    override fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction) {
        logger.i("Action=${action.type}(description={${action.description}} is executing on " +
                "global layout") // todo think about global layout
    }
}