package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.interceptors.interactors.AutoscrollProvider
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger
import io.reactivex.exceptions.CompositeException
import junit.framework.AssertionFailedError

class AutoscrollViewInteractor(
    private val logger: UiTestLogger
) : ViewInteractor, AutoscrollProvider<ViewInteraction> {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R {
        return try {
            action.invoke()
        } catch (errors: CompositeException) {
            errors.exceptions.forEach { error: Throwable ->
                if (error is AssertionFailedError || error is PerformException) {
                    return autoscroll(interaction, action, errors)
                }
            }
            throw errors
        } catch (error: AssertionFailedError) {
            autoscroll(interaction, action, error)
        } catch (error: PerformException) {
            autoscroll(interaction, action, error)
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