package com.kaspersky.uitest_framework.interceptors

import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.view.View
import android.webkit.WebView

/**
 * An interface for all atom interceptors, used in [WebAssertionProxy].
 */
interface WebAssertionInterceptor {

    /**
     * Called to do some stuff before [android.support.test.espresso.web.assertion.WebAssertion.checkResult] is actually
     * called.
     *
     * @param webAssertionProxy a proxy-wrapper of [android.support.test.espresso.web.assertion.WebAssertion] for
     *      interceptors calls.
     * @param view an Android [View], on which [android.support.test.espresso.web.assertion.WebAssertion] is performed.
     * @param result a result of [android.support.test.espresso.web.assertion.WebAssertion].
     */
    fun intercept(
        webAssertionProxy: WebAssertionProxy<*>,
        view: WebView?,
        result: Any
    )
}