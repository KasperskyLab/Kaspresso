package com.kaspersky.kaspresso.uiobjectloader

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction

/**
 * The interface to provide the functionality to load [androidx.test.uiautomator.UiObject2] in case of its absence or stale.
 */
interface UiObjectLoaderProvider {

    fun <T> handleUiObjectAbsence(interaction: UiObjectInteraction, action: () -> T): T
}