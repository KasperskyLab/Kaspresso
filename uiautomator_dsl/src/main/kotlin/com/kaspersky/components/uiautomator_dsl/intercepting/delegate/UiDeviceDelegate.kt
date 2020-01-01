package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.UiAutomatorDslConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

/**
 * Delegation class for [androidx.test.uiautomator.UiDevice].
 * Wraps all available public calls and intercepts into [check] and [perform].
 *
 * @see Delegate
 * @see Interceptor
 */
class UiDeviceDelegate(
    device: UiDevice
) : UiDelegate<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction> {

    override val interaction = UiDeviceInteraction(device)
    override var interceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiActionType,
        description: String? = null,
        assert: UiDevice.() -> Unit
    ) {
        val uiDeviceAssertion = getUiActionBase(type, description, assert)
        check(uiDeviceAssertion)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiActionType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) {
        val uiDeviceAction = getUiActionBase(type, description, action)
        perform(uiDeviceAction)
    }

    private fun getUiActionBase(
        type: UiActionType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) = UiActionBaseImpl(type, description, action)

    fun check(uiAssertion: UiDeviceAssertion) {
        if (!interceptCheck(uiAssertion)) interaction.check(uiAssertion)
    }

    fun perform(uiAction: UiDeviceAction) {
        if (!interceptPerform(uiAction)) interaction.perform(uiAction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun globalInterceptor(): UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? =
        UiAutomatorDslConfigurator.uiDeviceInterceptor
}