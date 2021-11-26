package com.kaspersky.components.composesupport.interceptors.behavior.impl.flakysafety

import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsBehaviorInterceptor] and [FlakySafetyProvider] interfaces.
 * Provides system flaky safety functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 */
class FlakySafeSemanticsBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : SemanticsBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderSimpleImpl(params, logger) {

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the intercepted [ComposeAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = flakySafely(action = activity)

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the intercepted [ComposeAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = flakySafely(action = activity)
}
