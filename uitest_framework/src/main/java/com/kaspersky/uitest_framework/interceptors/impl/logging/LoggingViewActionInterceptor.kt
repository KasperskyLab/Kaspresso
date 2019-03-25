package com.kaspersky.uitest_framework.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.ViewAction
import com.kaspersky.uitest_framework.interceptors.ViewActionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

class LoggingViewActionInterceptor(
    private val uiTestLogger: UiTestLogger
) : ViewActionInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        uiTestLogger.i("${viewAction.description} on ${view.describe()}")
    }
}