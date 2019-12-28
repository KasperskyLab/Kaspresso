package com.kaspersky.components.uiautomator_dsl.dsl.system

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptable
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperation

/**
 * Container class for UiAutomator action and assertions executing in the UiSystem.
 *
 * @see UiSystemActions
 */
object UiSystem : UiSystemActions, UiSystemAssertions,
    UiInterceptable<UiDeviceInteraction, UiOperation<UiDeviceInteraction>, UiOperation<UiDeviceInteraction>> {

    override val view: UiDeviceDelegate = UiDeviceDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    override val device: UiDeviceDelegate = view
}