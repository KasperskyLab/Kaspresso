package com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class LoggingObjectWatcherInterceptor(
    private val logger: UiTestLogger
) : ObjectWatcherInterceptor {

    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        logger.i("Check=${assertion.type}(description={${assertion.description}} is executing on " +
                "${interaction.elementClassName}) that was founded by selector=${interaction.selector.bySelector}")
    }

    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        logger.i("Action=${action.type}(description={${action.description}} is executing on " +
                "${interaction.elementClassName}) that was founded by selector=${interaction.selector.bySelector}")
    }
}