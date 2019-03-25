package com.kaspersky.kaspresso.interceptors.impl.logging

import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.webkit.WebView
import com.kaspersky.kaspresso.interceptors.WebAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.extensions.other.describe

/**
 * An implementation of [WebAssertionInterceptor] that logs info about
 * [android.support.test.espresso.web.assertion.WebAssertion].
 */
class LoggingWebAssertionInterceptor(
    private val logger: UiTestLogger
) : WebAssertionInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param webAssertionProxy a proxy-wrapper of [android.support.test.espresso.web.assertion.WebAssertion] for
     *      interceptors calls.
     * @param view an Android [android.view.View], on which [android.support.test.espresso.web.assertion.WebAssertion]
     *      is performed.
     * @param result a result of [android.support.test.espresso.web.assertion.WebAssertion].
     */
    override fun intercept(
        webAssertionProxy: WebAssertionProxy<*>,
        view: WebView?,
        result: Any
    ) {
        logger.i("${webAssertionProxy.describe()} on ${view.describe()} with result $result")
    }
}