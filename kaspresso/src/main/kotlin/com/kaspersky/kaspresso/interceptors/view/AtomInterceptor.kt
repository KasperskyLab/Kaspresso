package com.kaspersky.kaspresso.interceptors.view

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.proxy.AtomProxy

/**
 * An interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy].
 */
interface AtomInterceptor {

    /**
     * Called to do some stuff before [android.support.test.espresso.web.model.Atom.transform] is actually called.
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    fun intercept(atomProxy: AtomProxy<*>, evaluation: Evaluation?)
}