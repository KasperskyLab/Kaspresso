package com.kaspersky.kaspresso.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.ViewAction
import com.kaspersky.kaspresso.interceptors.ViewActionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.describe

class LoggingViewActionInterceptor(
    private val logger: UiTestLogger
): ViewActionInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        logger.i("${viewAction.description} on ${view.describe()}")
    }
}