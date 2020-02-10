@file:Suppress("unused")
package com.kaspersky.components.kautomator.screen

import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Interface with common actions for all UiAutomator screens
 *
 * Provides basic actions that can be performed on each and every screen
 *
 * @property view UiDeviceDelegate on which all actions are performed
 */
interface UiScreenActions {

    val view: UiDeviceInteractionDelegate

    /**
     * Simulates a short press on the BACK button.
     */
    fun pressBack() {
        view.perform(UiScreenActionType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenActionType : UiOperationType {
        PRESS_BACK
    }
}