package com.kaspersky.kaspresso.kakao_interceptors

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class ViewInteractionKakaoInterceptor(
    private val configurator: Configurator
) : InteractionKakaoInterceptor(configurator) {

    fun captureCheck(): (ViewInteraction, ViewAssertion) -> Unit = {
            viewInteraction, viewAssertion ->
        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            configurator.viewAssertionInterceptors
        )
        execute { viewInteraction.check(viewAssertionProxy) }
    }

    fun capturePerform(): (ViewInteraction, ViewAction) -> Unit = {
            viewInteraction, viewAction ->
        val viewActionProxy = ViewActionProxy(
            viewAction,
            configurator.viewActionInterceptors
        )
        execute { viewInteraction.perform(viewActionProxy) }
    }

}