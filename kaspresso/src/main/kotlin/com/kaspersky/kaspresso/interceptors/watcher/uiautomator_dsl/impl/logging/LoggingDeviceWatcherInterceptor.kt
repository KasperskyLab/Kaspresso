package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.impl.logging

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.DeviceWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class LoggingDeviceWatcherInterceptor(
    private val logger: UiTestLogger
) : DeviceWatcherInterceptor {

    override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion) {
        logger.i("Check=${assertion.getType()}(description={${assertion.getDescription()}} is executing on " +
                "global layout") // todo think about global layout
    }

    override fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction) {
        logger.i("Action=${action.getType()}(description={${action.getDescription()}} is executing on " +
                "global layout") // todo think about global layout
    }
}