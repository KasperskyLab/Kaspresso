package com.kaspersky.components.composesupport.interceptors.tolibrary

import com.kaspersky.components.composesupport.interceptors.behavior.SemanticsBehaviorInterceptor
import com.kaspersky.components.composesupport.interceptors.watcher.SemanticsWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * Kaspresso's implementation of Kakao-Compose ComposeInteraction interceptor.
 */
internal class ComposeSemanticsInterceptor(
    private val composeSemanticsBehaviorInterceptors: List<SemanticsBehaviorInterceptor>,
    private val composeSemanticsWatcherInterceptors: List<SemanticsWatcherInterceptor>
) : LibraryInterceptor<ComposeInteraction, ComposeAssertion, ComposeAction> {

    /**
     * Folds all [SemanticsBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [ComposeInteraction.check] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of all [SemanticsWatcherInterceptor] in the initial lambda
     */
    override fun interceptCheck(interaction: ComposeInteraction, assertion: ComposeAssertion) {
        composeSemanticsBehaviorInterceptors.fold(
            initial = {
                composeSemanticsWatcherInterceptors.forEach {
                    it.interceptCheck(interaction, assertion)
                }
                interaction.check(assertion)
            },
            operation = {
                    acc, deviceBehaviorInterceptor ->
                { deviceBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [SemanticsBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [ComposeInteraction.perform] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of [SemanticsWatcherInterceptor] in the initial lambda
     */
    override fun interceptPerform(interaction: ComposeInteraction, action: ComposeAction) {
        composeSemanticsBehaviorInterceptors.fold(
            initial = {
                composeSemanticsWatcherInterceptors.forEach {
                    it.interceptPerform(interaction, action)
                }
                interaction.perform(action)
            },
            operation = {
                    acc, deviceBehaviorInterceptor ->
                { deviceBehaviorInterceptor.interceptPerform(interaction, action, acc) }
            }
        ).invoke()
    }
}
