package com.kaspersky.components.uiautomator_dsl.dsl.screen

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate

/**
 * Interface with common actions for all UiAutomator screens
 *
 * Provides basic actions that can be performed on each and every screen
 *
 * @property view UiDeviceDelegate on which all actions are performed
 */
interface UiScreenActions {

    val view: UiDeviceDelegate

    /**
     * Simulates a short press on the BACK button.
     */
    fun pressBack() {
        view.perform(UiScreenActionType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenActionType : UiActionType {
        PRESS_BACK
    }
}