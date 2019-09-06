package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollWebBehaviorInterceptor(
    override val params: AutoScrollParams,
    private val logger: UiTestLogger
) : WebBehaviorInterceptor, AutoScrollProvider<Web.WebInteraction<*>> {

    override fun <R> intercept(interaction: Web.WebInteraction<*>, action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(params.allowedExceptions)) {
                return autoScroll(interaction, action, error)
            }
            throw error
        }
    }

    override fun <R> autoScroll(interaction: Web.WebInteraction<*>, action: () -> R, cachedError: Throwable): R {
        return try {
            interaction.perform(DriverAtoms.webScrollIntoView())
            logger.i("Web autoScroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}