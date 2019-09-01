package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInteractor

class AutoscrollWebBehaviorInteractor : WebBehaviorInteractor {

    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R {
        return try {
            action.invoke()
        } catch (e: PerformException) {
            val cachedError = e
            try {
                interaction.perform(DriverAtoms.webScrollIntoView())
                action.invoke()
            } catch (e: Throwable) {
                throw cachedError
            }
        }
    }
}