package com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging

import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.DeviceWatcherInterceptor
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