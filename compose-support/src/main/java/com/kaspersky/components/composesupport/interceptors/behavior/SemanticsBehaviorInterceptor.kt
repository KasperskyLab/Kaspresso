package com.kaspersky.components.composesupport.interceptors.behavior

import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The derived from [ComposeBehaviorInterceptor] interface for intercepting [ComposeInteraction.perform] and
 * [ComposeInteraction.check] behavior.
 */
interface SemanticsBehaviorInterceptor :
    ComposeBehaviorInterceptor<ComposeInteraction, ComposeAssertion, ComposeAction>
