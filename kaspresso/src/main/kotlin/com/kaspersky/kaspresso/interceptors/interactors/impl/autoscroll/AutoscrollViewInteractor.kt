package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.interceptors.interactors.AutoscrollInteractor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger
import junit.framework.AssertionFailedError

class AutoscrollViewInteractor(
    private val logger: UiTestLogger
) : ViewInteractor, AutoscrollInteractor<ViewInteraction> {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: AssertionFailedError) {
            autoscroll(interaction, action, error)
        } catch (error: PerformException) {
            autoscroll(interaction, action, error)
        }
    }

    override fun <R> autoscroll(interaction: ViewInteraction, action: () -> R, error: Throwable): R {
        val result: R = try {
            interaction.perform(ViewActions.scrollTo())
            action.invoke()
        } catch (e: Throwable) {
            throw error
        }

        logger.i("View autoscroll successfully performed.")
        return result
    }
}