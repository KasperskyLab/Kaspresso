package com.kaspersky.components.composesupport.interceptors.watcher.impl

import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsWatcherInterceptor] that logs info about [ComposeAssertion] or [ComposeAction]
 * and [ComposeInteraction] on which its activities are performing.
 */
class LoggingSemanticsWatcherInterceptor(
    private val logger: UiTestLogger
) : SemanticsWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    override fun interceptCheck(interaction: ComposeInteraction, assertion: ComposeAssertion) {
        logger.i(
            "Operation: Check=${assertion.type}(description={${assertion.description}}).\n" +
                    "ComposeInteraction: $interaction."
        )
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: ComposeInteraction, action: ComposeAction) {
        logger.i(
            "Operation: Perform=${action.type}(description={${action.description}}).\n" +
                    "ComposeInteraction: $interaction."
        )
    }
}
