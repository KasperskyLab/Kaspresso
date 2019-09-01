package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInteractor

class AutoscrollViewBehaviorInteractor : ViewBehaviorInteractor {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R {
        return try {
            action.invoke()
        } catch (e: PerformException) {
            val cachedError = e
            try {
                interaction.perform(ViewActions.scrollTo())
                action.invoke()
            } catch (e: Throwable) {
                throw cachedError
            }
        }
    }
}