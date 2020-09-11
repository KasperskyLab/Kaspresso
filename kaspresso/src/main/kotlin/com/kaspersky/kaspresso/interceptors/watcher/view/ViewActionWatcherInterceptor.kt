package com.kaspersky.kaspresso.interceptors.watcher.view

import android.view.View
import androidx.test.espresso.ViewAction

/**
 * The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewActionProxy].
 */
interface ViewActionWatcherInterceptor {

    /**
     * Called to do some stuff before [ViewAction.perform] is actually called.
     *
     * @param viewAction responsible for performing an interaction on the given [View] element.
     * @param view an Android [View], on which [viewAction] is performed.
     */
    fun intercept(viewAction: ViewAction, view: View)
}
