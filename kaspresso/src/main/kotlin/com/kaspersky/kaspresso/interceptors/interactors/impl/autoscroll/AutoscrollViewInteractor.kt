package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.*
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor

class AutoscrollViewInteractor : ViewInteractor {

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