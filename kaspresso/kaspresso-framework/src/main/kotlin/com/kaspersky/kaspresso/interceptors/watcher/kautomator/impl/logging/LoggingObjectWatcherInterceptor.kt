package com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ObjectWatcherInterceptor] that logs info about [UiObjectAssertion] or [UiObjectAction]
 * and [UiObjectInteraction] on which its activities are performing.
 */
class LoggingObjectWatcherInterceptor(
    private val logger: UiTestLogger
) : ObjectWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        logger.i(
            "The object: ${interaction.description}. " +
                    "The operation: Check=${assertion.type}(description={${assertion.description}}. " +
                    "Additional info: the object was founded by selector=${interaction.selector.bySelector}"
        )
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        logger.i(
            "The object: ${interaction.description}. " +
                    "The operation: Action=${action.type}(description={${action.description}}. " +
                    "Additional info: the object was founded by selector=${interaction.selector.bySelector}"
        )
    }
}
