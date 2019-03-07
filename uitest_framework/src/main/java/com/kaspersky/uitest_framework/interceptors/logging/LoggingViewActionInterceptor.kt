package com.kaspersky.uitest_framework.interceptors.logging

import android.support.test.espresso.ViewAction
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.ViewActionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

class LoggingViewActionInterceptor(
        private val uiTestLogger: UiTestLogger
) : ViewActionInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        uiTestLogger.i("${viewAction.description} on ${view.describe()}")
    }
}