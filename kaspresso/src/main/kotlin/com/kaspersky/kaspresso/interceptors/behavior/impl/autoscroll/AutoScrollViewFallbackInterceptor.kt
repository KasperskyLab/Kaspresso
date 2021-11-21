package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.autoscroll.AutoScrollFallbackProviderImpl
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams

class AutoScrollViewFallbackInterceptor (
    params: AutoScrollParams,
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    AutoScrollProvider<ViewInteraction> by AutoScrollFallbackProviderImpl(params, logger) {

    /**
     * Wraps the given [action] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [ViewInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = withAutoScroll(interaction, action)
}

