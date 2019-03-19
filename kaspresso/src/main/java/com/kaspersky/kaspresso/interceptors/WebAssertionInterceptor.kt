package com.kaspersky.kaspresso.interceptors

import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.webkit.WebView

interface WebAssertionInterceptor {

    fun intercept(
        webAssertionProxy: WebAssertionProxy<*>,
        view: WebView?,
        result: Any
    )
}