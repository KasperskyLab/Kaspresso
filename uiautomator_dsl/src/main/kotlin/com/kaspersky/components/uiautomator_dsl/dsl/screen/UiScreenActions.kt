package com.kaspersky.components.uiautomator_dsl.dsl.screen

import com.kaspersky.components.uiautomator_dsl.intercepting.actions.device.UiDeviceActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiDeviceProxy

interface UiScreenActions {

    val uiDeviceProxy: UiDeviceProxy

    /**
     * Performs click on device's back button
     */
    fun pressBack() {
        uiDeviceProxy.perform(UiScreenActionType.PRESS_BACK) { pressBack() }
    }

    enum class UiScreenActionType : UiDeviceActionType {
        PRESS_BACK
    }

}