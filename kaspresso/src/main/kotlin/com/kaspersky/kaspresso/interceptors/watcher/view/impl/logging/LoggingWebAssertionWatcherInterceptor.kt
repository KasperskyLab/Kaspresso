package com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging

import android.webkit.WebView
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.assertion.describeTo
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.StringDescription

/**
 * The implementation of [WebAssertionWatcherInterceptor] that logs info about
 * [androidx.test.espresso.web.assertion.WebAssertion].
 */
class LoggingWebAssertionWatcherInterceptor(
    private val logger: UiTestLogger
) : WebAssertionWatcherInterceptor {

    /**
     * Writes info to [compositeLogger].
     *
     * @param webAssertionProxy a proxy-wrapper of [androidx.test.espresso.web.assertion.WebAssertion] for
     *      interceptors calls.
     * @param view an Android [android.view.View], on which [androidx.test.espresso.web.assertion.WebAssertion]
     *      is performed.
     * @param result a result of [androidx.test.espresso.web.assertion.WebAssertion].
     */
    override fun intercept(webAssertionProxy: WebAssertionProxy<*>, view: WebView?, result: Any) {
        logger.i(getFullWebAssertionDescription(webAssertionProxy, result))
    }

    /**
     * @return a string description of [WebAssertion].
     */
    private fun getFullWebAssertionDescription(webAssertionProxy: WebAssertionProxy<*>, result: Any): String {
        return StringBuilder("web assertion")
            .apply {
                webAssertionProxy.webAssertion.describeTo(this, result)
            }
            .apply {
                append(" on webview ")
                webAssertionProxy.matcher.describeTo(StringDescription(this))
            }
            .toString()
    }
}
