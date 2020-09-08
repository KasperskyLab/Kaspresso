package com.kaspersky.kaspresso.interceptors.watcher.view

import androidx.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.proxy.AtomProxy

/**
 * The interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy].
 */
interface AtomWatcherInterceptor {

    /**
     * Called to do some stuff before [androidx.test.espresso.web.model.Atom.transform] is actually called.
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    fun intercept(atomProxy: AtomProxy<*>, evaluation: Evaluation?)
}
