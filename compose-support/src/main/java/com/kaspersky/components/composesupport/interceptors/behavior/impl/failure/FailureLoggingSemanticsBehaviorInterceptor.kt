package com.kaspersky.components.composesupport.interceptors.behavior.impl.failure

import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The implementation of [SemanticsBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [ComposeInteraction.perform] and [ComposeInteraction.check] calls.
 *
 * Important!
 * By default, the interceptor is not used in Kaspresso because this one pollutes logs by error messages that may confuse a user.
 * If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider] directly.
 */
class FailureLoggingSemanticsBehaviorInterceptor(
    logger: UiTestLogger
) : SemanticsBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param assertion the intercepted [ComposeAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: ComposeInteraction,
        assertion: ComposeAssertion,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)

    /**
     * Wraps the given [activity] invocation with the failure logging.
     *
     * @param interaction the intercepted [ComposeInteraction].
     * @param action the intercepted [ComposeAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: ComposeInteraction,
        action: ComposeAction,
        activity: () -> T
    ): T = withLoggingOnFailure(action = activity)
}
