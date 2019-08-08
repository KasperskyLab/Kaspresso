package com.kaspersky.kaspresso.interceptors.view

import android.webkit.WebView
import androidx.test.espresso.web.assertion.WebAssertionProxy

/**
 * An interface for all atom interceptors, used in [WebAssertionProxy].
 */
interface WebAssertionInterceptor {

    /**
     * Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult] is actually
     * called.
     *
     * @param webAssertionProxy a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion] for
     *      interceptors calls.
     * @param view an Android [View], on which [androidx.test.espresso.web.assertion.WebAssertion] is performed.
     * @param result a result of [androidx.test.espresso.web.assertion.WebAssertion].
     */
    fun intercept(
        webAssertionProxy: WebAssertionProxy<*>,
        view: WebView?,
        result: Any
    )
}