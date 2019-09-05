package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollWebInteractor(
    override val params: AutoScrollParams,
    private val logger: UiTestLogger
) : WebInteractor, AutoScrollProvider<Web.WebInteraction<*>> {

    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (params.isExceptionAllowed(error)) {
                return autoscroll(interaction, action, error)
            }
            throw error
        }
    }

    override fun <R> autoscroll(interaction: Web.WebInteraction<*>, action: () -> R, cachedError: Throwable): R {
        return try {
            interaction.perform(DriverAtoms.webScrollIntoView())
            logger.i("Web autoscroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}