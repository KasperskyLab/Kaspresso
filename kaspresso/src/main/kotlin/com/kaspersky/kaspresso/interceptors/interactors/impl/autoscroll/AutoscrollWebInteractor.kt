package com.kaspersky.kaspresso.interceptors.interactors.impl.autoscroll

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor

class AutoscrollWebInteractor : WebInteractor {

    override fun <R> interact(
        view: Any?,
        interaction: Web.WebInteraction<*>,
        interactable: () -> R
    ): R {
        return try {
            interactable.invoke()
        } catch (e: NoMatchingViewException) {
            val cachedError = e
            try {
                interaction.perform(
                    DriverAtoms.webScrollIntoView()
                )
                interactable.invoke()
            } catch (e: Throwable) {
                throw cachedError
            }
        }
    }
}