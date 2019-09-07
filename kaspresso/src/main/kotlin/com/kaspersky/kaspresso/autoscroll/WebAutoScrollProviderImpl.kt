package com.kaspersky.kaspresso.autoscroll

import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class WebAutoScrollProviderImpl(
    private val params: AutoScrollParams,
    private val logger: UiTestLogger
) : AutoScrollProvider<Web.WebInteraction<*>> {

    @Throws(Throwable::class)
    override fun <T> withAutoScroll(interaction: Web.WebInteraction<*>, action: () -> T): T {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(params.allowedExceptions)) {
                return scroll(interaction, action, error)
            }
            throw error
        }
    }

    @Throws(Throwable::class)
    override fun <T> scroll(interaction: Web.WebInteraction<*>, action: () -> T, cachedError: Throwable): T {
        return try {
            interaction.perform(DriverAtoms.webScrollIntoView())
            logger.i("Web autoScroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}