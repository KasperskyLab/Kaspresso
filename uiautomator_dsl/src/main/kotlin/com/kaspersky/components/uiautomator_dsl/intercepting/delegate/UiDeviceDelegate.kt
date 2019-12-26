package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

class UiDeviceDelegate(
    device: UiDevice
) : UiDelegate<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    override val interaction = UiDeviceInteraction(device)
    override var interceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiOperationType,
        description: String? = null,
        assert: UiDevice.() -> Unit
    ) {
        val uiDeviceAssertion = getUiDeviceOperation(type, description, assert)
        check(uiDeviceAssertion)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) {
        val uiDeviceAction = getUiDeviceOperation(type, description, action)
        perform(uiDeviceAction)
    }

    private fun getUiDeviceOperation(
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) = UiDeviceOperation(type, description, action)

    fun check(uiDeviceAssertion: UiDeviceAssertion) {
        if (!interceptCheck(uiDeviceAssertion)) uiDeviceAssertion.execute(interaction)
    }

    fun perform(uiDeviceAction: UiDeviceAction) {
        if (!interceptPerform(uiDeviceAction)) uiDeviceAction.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun kakaoInterceptor(): UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? =
        UiAutomatorConfigurator.uiDeviceInterceptor
}