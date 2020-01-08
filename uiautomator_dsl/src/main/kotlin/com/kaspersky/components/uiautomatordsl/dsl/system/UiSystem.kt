package com.kaspersky.components.uiautomatordsl.dsl.system

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion
import com.kaspersky.components.uiautomatordsl.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomatordsl.intercepting.intercept.UiInterceptable

/**
 * Container class for UiAutomator action and assertions executing in the UiSystem.
 *
 * @see UiSystemActions
 */
object UiSystem : UiSystemActions, UiSystemAssertions,
    UiInterceptable<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    override val view: UiDeviceDelegate = UiDeviceDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    operator fun invoke(function: UiSystem.() -> Unit) {
        function.invoke(this)
    }
}