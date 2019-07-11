package com.kaspersky.kaspresso.kakao_interceptors

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class DataInteractionKakaoInterceptor(
    private val configurator: Configurator
) : InteractionKakaoInterceptor(configurator) {

    fun captureCheck(): (DataInteraction, ViewAssertion) -> Unit = {
            dataInteraction, viewAssertion ->
        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            configurator.viewAssertionInterceptors
        )
        execute { dataInteraction.check(viewAssertionProxy) }
    }
}