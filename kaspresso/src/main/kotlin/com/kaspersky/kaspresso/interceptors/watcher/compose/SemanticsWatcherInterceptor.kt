package com.kaspersky.kaspresso.interceptors.watcher.compose

import com.kaspersky.kaspresso.interceptors.watcher.kautomator.KautomatorWatcherInterceptor
import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction
import io.github.kakaocup.compose.intercept.operation.ComposeAction
import io.github.kakaocup.compose.intercept.operation.ComposeAssertion

/**
 * The derived from [SemanticsWatcherInterceptor] interface for intercepting (only watching) [ComposeInteraction.perform] and
 * [ComposeInteraction.check] behavior.
 */
interface SemanticsWatcherInterceptor :
    KautomatorWatcherInterceptor<ComposeInteraction, ComposeAssertion, ComposeAction>
