package com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.Evaluation
import androidx.test.espresso.web.webdriver.describeTo
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.proxy.AtomProxy
import org.hamcrest.StringDescription

/**
 * The implementation of [AtomWatcherInterceptor] that logs info about web action.
 */
class LoggingAtomWatcherInterceptor(
    private val logger: UiTestLogger
) : AtomWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    override fun intercept(atomProxy: AtomProxy<*>, evaluation: Evaluation?) {
        logger.i(getFullAtomDescription(atomProxy, evaluation))
    }

    /**
     * @return a string description of [Atom].
     */
    private fun getFullAtomDescription(atomProxy: AtomProxy<*>, evaluation: Evaluation?): String {
        return StringBuilder("web action")
            .apply {
                atomProxy.atom.describeTo(this, evaluation)
            }
            .apply {
                append(" on webview ")
                atomProxy.matcher.describeTo(StringDescription(this))
            }
            .toString()
    }
}
