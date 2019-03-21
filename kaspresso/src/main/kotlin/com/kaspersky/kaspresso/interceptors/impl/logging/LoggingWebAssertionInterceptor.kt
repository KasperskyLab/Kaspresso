package com.kaspersky.kaspresso.interceptors.impl.logging

import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.WebAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.describe

class LoggingWebAssertionInterceptor(
    private val logger: UiTestLogger
) : WebAssertionInterceptor {

    override fun intercept(
        webAssertionProxy: WebAssertionProxy<*>,
        view: WebView?,
        result: Any
    ) {
        logger.i("${webAssertionProxy.describe()} on ${view.describe()} with result $result")
    }
}