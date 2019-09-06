package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class AutoScrollViewBehaviorInterceptor(
    override val params: AutoScrollParams,
    private val logger: UiTestLogger
) : ViewBehaviorInterceptor, AutoScrollProvider<ViewInteraction> {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R {
        return try {
            action.invoke()
        } catch (error: Throwable) {
            if (error.isAllowed(params.allowedExceptions)) {
                return autoScroll(interaction, action, error)
            }
            throw error
        }
    }

    override fun <R> autoScroll(interaction: ViewInteraction, action: () -> R, cachedError: Throwable): R {
        return try {
            interaction.perform(ViewActions.scrollTo())
            logger.i("View autoScroll successfully performed.")
            action.invoke()
        } catch (error: Throwable) {
            throw cachedError
        }
    }
}