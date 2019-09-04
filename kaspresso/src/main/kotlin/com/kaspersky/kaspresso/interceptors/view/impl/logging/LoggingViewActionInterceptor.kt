package com.kaspersky.kaspresso.interceptors.view.impl.logging

import android.view.View
import androidx.test.espresso.ViewAction
import com.kaspersky.kaspresso.internal.extensions.other.describe
import com.kaspersky.kaspresso.interceptors.view.ViewActionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of [ViewActionInterceptor] that logs info about [ViewAction].
 */
class LoggingViewActionInterceptor(
    private val logger: UiTestLogger
) : ViewActionInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param viewAction responsible for performing an interaction on the given [View] element.
     * @param view an Android [View], on which [viewAction] is performed.
     */
    override fun intercept(viewAction: ViewAction, view: View) {
        logger.i("${viewAction.description} on ${view.describe()}")
    }
}