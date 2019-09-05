package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollViewInteractor(
    override val params: AutoScrollParams,
    private val logger: UiTestLogger
) : ViewInteractor, AutoScrollProvider<ViewInteraction> {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (params.isExceptionAllowed(error)) {
                return autoscroll(interaction, action, error)
            }
            throw error
        }
    }

    override fun <R> autoscroll(interaction: ViewInteraction, action: () -> R, cachedError: Throwable): R {
        return try {
            interaction.perform(ViewActions.scrollTo())
            logger.i("View autoscroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}