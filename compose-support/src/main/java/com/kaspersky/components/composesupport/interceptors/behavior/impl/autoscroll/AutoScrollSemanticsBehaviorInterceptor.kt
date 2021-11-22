package com.kaspersky.components.composesupport.interceptors.behavior.impl.autoscroll

import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.components.composesupport.autoscroll.SemanticsAutoScrollProviderImpl
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsBehaviorInterceptor] and [AutoScrollProvider] interfaces.
 * Provides autoscroll on failure functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class AutoScrollSemanticsBehaviorInterceptor(
    logger: UiTestLogger,
    autoScrollParams: AutoScrollParams
) : SemanticsBehaviorInterceptor,
    AutoScrollProvider<ComposeInteraction> by SemanticsAutoScrollProviderImpl(logger, autoScrollParams) {

    /**
     * Wraps the given [assertion] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the assertion to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = withAutoScroll(interaction, activity)

    /**
     * Wraps the given [action] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the assertion to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = withAutoScroll(interaction, activity)
}
