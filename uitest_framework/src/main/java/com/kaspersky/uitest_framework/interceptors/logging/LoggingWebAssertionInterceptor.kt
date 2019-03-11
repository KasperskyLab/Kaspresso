package com.kaspersky.uitest_framework.interceptors.logging

import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.webkit.WebView
import com.kaspersky.uitest_framework.interceptors.WebAssertionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

class LoggingWebAssertionInterceptor(
        private val uiTestLogger: UiTestLogger
): WebAssertionInterceptor {

    override fun intercept(
            webAssertionProxy: WebAssertionProxy<*>,
            view: WebView?,
            result: Any
    ) {
        uiTestLogger.i(
                "${webAssertionProxy.describe()} on ${view.describe()} with result $result"
        )
    }
}