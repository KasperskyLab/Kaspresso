package com.kaspersky.components.uiautomatordsl.intercepting.interaction

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion

/**
 * Provides an interaction to work with the UiDevice
 */
class UiDeviceInteraction(
    val device: UiDevice
) : UiInteraction<UiDeviceAssertion, UiDeviceAction> {

    override fun check(assertion: UiDeviceAssertion) {
        assertion.execute(device)
    }

    override fun perform(action: UiDeviceAction) {
        action.execute(device)
    }
}