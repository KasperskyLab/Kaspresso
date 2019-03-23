package com.kaspersky.uitest_framework.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.ViewAction
import com.kaspersky.uitest_framework.interceptors.ViewActionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

/**
 * An implementation of [ViewActionInterceptor] that logs info about [ViewAction].
 */
class LoggingViewActionInterceptor(
    private val uiTestLogger: UiTestLogger
) : ViewActionInterceptor {

    /**
     * Writes info to [uiTestLogger].
     *
     * @param viewAction responsible for performing an interaction on the given [View] element.
     * @param view an Android [View], on which [viewAction] is performed.
     */
    override fun intercept(viewAction: ViewAction, view: View) {
        uiTestLogger.i("${viewAction.description} on ${view.describe()}")
    }
}