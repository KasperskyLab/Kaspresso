package com.kaspersky.components.composesupport.interceptors.behavior.impl.elementloader

import com.kaspersky.kaspresso.elementloader.ElementLoaderProvider
import com.kaspersky.kaspresso.elementloader.ElementLoaderProviderImpl
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.ElementLoaderParams
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsBehaviorInterceptor] and [ElementLoaderProvider] interfaces.
 * Provides element reloading on failure functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class ElementLoaderSemanticsBehaviorInterceptor(
    logger: UiTestLogger,
    params: ElementLoaderParams
) : SemanticsBehaviorInterceptor,
    ElementLoaderProvider by ElementLoaderProviderImpl(logger, params) {

    /**
     * Wraps the given [assertion] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the assertion to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindNode() },
        action = activity
    )

    /**
     * Wraps the given [action] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the assertion to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindNode() },
        action = activity
    )
}
