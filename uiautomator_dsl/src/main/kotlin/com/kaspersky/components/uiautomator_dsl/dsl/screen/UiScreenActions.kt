package com.kaspersky.components.uiautomator_dsl.dsl.screen

import androidx.test.uiautomator.UiDevice

// todo create stack of <INTERACTION, ASSERTION, ACTION> for UiDevice (Screen/System)
interface UiScreenActions {

    val uiDevice: UiDevice

    /**
     * Performs click on device's back button
     */
    fun pressBack() {
        uiDevice.pressBack()
    }

}