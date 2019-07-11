package com.kaspersky.kaspresso.kakao_interceptors

import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.AtomProxy

internal class WebInteractionKakaoInterceptor(
    private val configurator: Configurator
) : InteractionKakaoInterceptor(configurator) {

    fun captureCheck(): (Web.WebInteraction<*>, WebAssertion<*>) -> Unit = {
            webInteraction, webAssertion ->
        // todo
        // 1. we need Atom here (I think it will be through interceptor lambda)
        // 2. why we need the matcher in WebAssertionProxy
//        val webAssertionProxy = WebAssertionProxy(
//            webAssertion,
//            atom,
//            matcher,
//            configurator.webAssertionInterceptors
//        )
//        execute { webInteraction.check(webAssertionProxy) }
        execute { webInteraction.check(webAssertion) }
    }

    fun capturePerform(): (Web.WebInteraction<*>, Atom<*>) -> Unit = {
            webInteraction, atom ->
        val webActionProxy = AtomProxy(
            atom,
            configurator.atomInterceptors
        )
        execute { webInteraction.perform(webActionProxy) }
    }
}