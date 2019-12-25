package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.operations.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiDeviceProxy(
    device: UiDevice
) : Proxy<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    companion object {
        private const val EMPTY_STRING: String = ""
    }

    override val interaction = UiDeviceInteraction(device)

    override var interceptor: Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiOperationType,
        description: String = EMPTY_STRING,
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
        description: String = EMPTY_STRING,
        action: UiDevice.() -> Unit
    ) {
        val uiDeviceAction = getUiDeviceOperation(type, description, action)
        perform(uiDeviceAction)
    }

    private fun getUiDeviceOperation(
        type: UiOperationType,
        description: String = EMPTY_STRING,
        action: UiDevice.() -> Unit
    ) = UiDeviceOperationImpl(type, description, action)

    fun check(uiDeviceAssertion: UiDeviceAssertion) {
        if (!interceptCheck(uiDeviceAssertion)) uiDeviceAssertion.execute(interaction)
    }

    fun perform(uiDeviceAction: UiDeviceAction) {
        if (!interceptPerform(uiDeviceAction)) uiDeviceAction.execute(interaction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun kakaoInterceptor(): Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? =
        UiAutomatorConfigurator.uiDeviceInterceptor
}