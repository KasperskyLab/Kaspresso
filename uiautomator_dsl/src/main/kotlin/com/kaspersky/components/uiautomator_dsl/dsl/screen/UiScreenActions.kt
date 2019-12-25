package com.kaspersky.components.uiautomator_dsl.dsl.screen

import com.kaspersky.components.uiautomator_dsl.intercepting.operations.UiOperationType
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiDeviceProxy

interface UiScreenActions {

    val uiDeviceProxy: UiDeviceProxy

    /**
     * Performs click on device's back button
     */
    fun pressBack() {
        uiDeviceProxy.perform(UiScreenOperationType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenOperationType : UiOperationType {
        PRESS_BACK
    }

}