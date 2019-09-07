package com.kaspersky.kaspresso.autoscroll

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollProviderImpl(
    private val params: AutoScrollParams,
    private val logger: UiTestLogger
) : AutoScrollProvider<ViewInteraction> {

    @Throws(Throwable::class)
    override fun <T> withAutoScroll(interaction: ViewInteraction, action: () -> T): T {
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
    override fun <T> scroll(interaction: ViewInteraction, action: () -> T, cachedError: Throwable): T {
        return try {
            interaction.perform(ViewActions.scrollTo())
            logger.i("View autoScroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}