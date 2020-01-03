package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.impl.logging

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class LoggingObjectWatcherInterceptor(
    private val logger: UiTestLogger
) : ObjectWatcherInterceptor {

    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        logger.i("Check=${assertion.getType()}(description={${assertion.getDescription()}} is executing on " +
                "${interaction.elementClassName}) that was founded by selector=${interaction.selector}")
    }

    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        logger.i("Action=${action.getType()}(description={${action.getDescription()}} is executing on " +
                "${interaction.elementClassName}) that was founded by selector=${interaction.selector}")
    }
}