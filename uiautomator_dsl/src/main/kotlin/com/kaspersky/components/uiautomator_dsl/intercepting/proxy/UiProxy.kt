package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`.UiActionImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.`object`.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.`object`.UiAssertImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.`object`.UiAssertType
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiProxy(
    device: UiDevice,
    selector: BySelector,
    elementClassName: String
) : Proxy<UiInteraction, UiAssert, UiAction> {

    companion object {
        private const val EMPTY_STRING: String = ""
    }

    override val interaction: UiInteraction = UiInteraction(device, selector, elementClassName)
    override var interceptor: Interceptor<UiInteraction, UiAssert, UiAction>? = null

    fun loadView() {
        interaction.tryToFindUiObject()
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(
        type: UiAssertType,
        description: String = EMPTY_STRING,
        assert: UiObject2.() -> Unit
    ) {
        val uiAssert: UiAssert =
            UiAssertImpl(
                type,
                description,
                assert
            )
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction)
    }

    fun check(uiAssert: UiAssert) {
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(
        type: UiActionType,
        description: String = EMPTY_STRING,
        action: UiObject2.() -> Unit
    ) {
        val uiAction: UiAction =
            UiActionImpl(
                type,
                description,
                action
            )
        if (!interceptPerform(uiAction)) uiAction.perform(interaction)
    }

    fun perform(uiAction: UiAction) {
        if (!interceptPerform(uiAction)) uiAction.perform(interaction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiInteraction, UiAssert, UiAction>> =
        UiScreen.UI_INTERCEPTORS

    override fun kakaoInterceptor(): Interceptor<UiInteraction, UiAssert, UiAction>? =
        UiAutomatorConfigurator.uiInterceptor

}