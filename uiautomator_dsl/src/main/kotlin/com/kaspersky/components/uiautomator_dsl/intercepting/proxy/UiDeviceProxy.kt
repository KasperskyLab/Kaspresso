package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.device.UiDeviceAction
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.device.UiDeviceActionImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.device.UiDeviceActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device.UiDeviceAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device.UiDeviceAssertImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device.UiDeviceAssertType
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiDeviceProxy(
    device: UiDevice
) : Proxy<UiDeviceInteraction, UiDeviceAssert, UiDeviceAction> {

    companion object {
        private const val EMPTY_STRING: String = ""
    }

    override val interaction = UiDeviceInteraction(device)

    override var interceptor: Interceptor<UiDeviceInteraction, UiDeviceAssert, UiDeviceAction>? = null

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiDeviceAssertType,
        description: String = EMPTY_STRING,
        assert: UiDevice.() -> Unit
    ) {
        val uiAssert: UiDeviceAssert =
            UiDeviceAssertImpl(
                type,
                description,
                assert
            )
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction)
    }

    fun check(uiAssert: UiDeviceAssert) {
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiDeviceActionType,
        description: String = EMPTY_STRING,
        action: UiDevice.() -> Unit
    ) {
        val uiAction: UiDeviceAction =
            UiDeviceActionImpl(
                type,
                description,
                action
            )
        if (!interceptPerform(uiAction)) uiAction.perform(interaction)
    }

    fun perform(uiAction: UiDeviceAction) {
        if (!interceptPerform(uiAction)) uiAction.perform(interaction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiDeviceInteraction, UiDeviceAssert, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun kakaoInterceptor(): Interceptor<UiDeviceInteraction, UiDeviceAssert, UiDeviceAction>? =
        UiAutomatorConfigurator.uiDeviceInterceptor
}