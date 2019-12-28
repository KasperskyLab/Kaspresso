package com.kaspersky.components.uiautomator_dsl.dsl.screen

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate

/**
 * Interface with common actions for all UiAutomator screens
 *
 * Provides basic actions that can be performed on each and every screen
 *
 * @property device UiDeviceDelegate on which all actions are performed
 */
interface UiScreenActions {

    val device: UiDeviceDelegate

    /**
     * Simulates a short press on the BACK button.
     */
    fun pressBack() {
        device.perform(UiScreenOperationType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenOperationType : UiOperationType {
        PRESS_BACK
    }

}