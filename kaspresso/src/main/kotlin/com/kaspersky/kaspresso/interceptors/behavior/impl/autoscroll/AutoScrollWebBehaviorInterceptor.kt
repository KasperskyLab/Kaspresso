package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollWebBehaviorInterceptor(
    params: AutoScrollParams,
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    AutoScrollProvider<Web.WebInteraction<*>> by WebAutoScrollProviderImpl(params, logger) {

    @Throws(Throwable::class)
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T =
        withAutoScroll(interaction, action)
}