package com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging

import android.view.View
import androidx.test.espresso.ViewAction
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.describe
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ViewActionWatcherInterceptor] that logs info about [ViewAction].
 */
class LoggingViewActionWatcherInterceptor(
    private val logger: UiTestLogger
) : ViewActionWatcherInterceptor {

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
