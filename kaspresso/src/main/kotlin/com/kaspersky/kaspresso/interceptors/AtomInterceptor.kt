package com.kaspersky.kaspresso.interceptors

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.Evaluation

/**
 * An interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy].
 */
interface AtomInterceptor {

    /**
     * Called to do some stuff before [android.support.test.espresso.web.model.Atom.transform] is actually called.
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    fun <R> intercept(
        evaluation: Evaluation?,
        atom: Atom<R>,
        lastArgs: MutableList<Any>?
    )
}