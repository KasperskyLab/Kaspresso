package com.kaspersky.components.uiautomator_dsl.intercepting.proxy

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.UiAutomatorConfigurator
import com.kaspersky.components.uiautomator_dsl.dsl.screen.UiScreen
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiActionImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssertImpl
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor

class UiObject2Proxy(selector: BySelector) : Proxy<UiInteraction, UiAssert, UiAction> {

    override val interaction: UiInteraction = UiInteraction(selector)
    override var interceptor: Interceptor<UiInteraction, UiAssert, UiAction>? = null

    fun loadView() {
        interaction.tryToFindUiObject()
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun check(description: String, assert: UiObject2.() -> Unit) {
        val uiAssert: UiAssert =
            UiAssertImpl(
                description,
                assert
            )
        if (!interceptCheck(uiAssert)) uiAssert.check(interaction)
    }

    /**
     * @action must throw exception if something went wrong
     */
    fun perform(description: String, action: UiObject2.() -> Unit) {
        val uiAction: UiAction =
            UiActionImpl(
                description,
                action
            )
        if (!interceptPerform(uiAction)) uiAction.perform(interaction)
    }

    override fun screenInterceptors(): Iterable<Interceptor<UiInteraction, UiAssert, UiAction>> =
        UiScreen.uiInterceptors

    override fun kakaoInterceptor(): Interceptor<UiInteraction, UiAssert, UiAction>? =
        UiAutomatorConfigurator.uiInterceptor

}