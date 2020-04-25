package com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.DeviceWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [DeviceWatcherInterceptor] that logs info about [UiDeviceAssertion] or [UiDeviceAction]
 * and [UiDeviceInteraction] on which its activities are performing.
 */
class LoggingDeviceWatcherInterceptor(
    private val logger: UiTestLogger
) : DeviceWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion) {
        logger.i("Check=${assertion.type}(description={${assertion.description}} is executing")
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction) {
        logger.i("Action=${action.type}(description={${action.description}} is executing")
    }
}
