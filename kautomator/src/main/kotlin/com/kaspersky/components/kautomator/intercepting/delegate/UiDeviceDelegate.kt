package com.kaspersky.components.kautomator.intercepting.delegate

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.KautomatorConfigurator
import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptor
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationBaseImpl
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

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
        type: UiOperationType,
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
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) {
        val uiDeviceAction = getUiActionBase(type, description, action)
        perform(uiDeviceAction)
    }

    private fun getUiActionBase(
        type: UiOperationType,
        description: String? = null,
        action: UiDevice.() -> Unit
    ) = UiOperationBaseImpl(type, description, action)

    fun check(uiAssertion: UiDeviceAssertion) {
        if (!interceptCheck(uiAssertion)) interaction.check(uiAssertion)
    }

    fun perform(uiAction: UiDeviceAction) {
        if (!interceptPerform(uiAction)) interaction.perform(uiAction)
    }

    override fun screenInterceptors(): Iterable<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> =
        UiScreen.UI_DEVICE_INTERCEPTORS

    override fun globalInterceptor(): UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? =
        KautomatorConfigurator.uiDeviceInterceptor
}