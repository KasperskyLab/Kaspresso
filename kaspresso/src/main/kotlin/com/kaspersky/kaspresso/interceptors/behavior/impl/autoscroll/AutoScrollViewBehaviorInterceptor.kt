package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.autoscroll.AutoScrollProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollViewBehaviorInterceptor(
    params: AutoScrollParams,
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    AutoScrollProvider<ViewInteraction> by AutoScrollProviderImpl(params, logger) {

    @Throws(Throwable::class)
    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = withAutoScroll(interaction, action)
}