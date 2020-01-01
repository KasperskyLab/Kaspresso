package com.kaspersky.components.uiautomator_dsl.intercepting.interaction

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAssertion

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