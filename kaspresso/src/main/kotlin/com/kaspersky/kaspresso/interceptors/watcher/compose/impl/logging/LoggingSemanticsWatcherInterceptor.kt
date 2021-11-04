package com.kaspersky.kaspresso.interceptors.watcher.compose.impl.logging

import com.kaspersky.kaspresso.interceptors.watcher.compose.SemanticsWatcherInterceptor
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
        // todo
        logger.i("Compose assertion")
    }

    /**
     * Writes info to [logger].
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    override fun interceptPerform(interaction: ComposeInteraction, action: ComposeAction) {
        // todo
        logger.i("Compose action")
    }
}
