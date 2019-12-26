package com.kaspersky.components.uiautomator_dsl.dsl.screen

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate

interface UiScreenActions {

    val proxyDevice: UiDeviceDelegate

    /**
     * Performs click on device's back button
     */
    fun pressBack() {
        proxyDevice.perform(UiScreenOperationType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenOperationType : UiOperationType {
        PRESS_BACK
    }

}