package com.kaspersky.kaspresso.proxy

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.AtomInterceptor

/**
 * A proxy-wrapper of [Atom] for interceptors calls.
 */
class AtomProxy<R>(
    private val atom: Atom<R>,
    private val interceptors: List<AtomInterceptor>
) : Atom<R> {

    private var lastArgs: MutableList<Any>? = null

    /**
     * Simply calls [Atom.getArguments] on wrapped [atom].
     *
     * @param elementContext null unless an ElementReference has been supplied to execute this atom with.
     * @return a [List] of objects to pass to the script as arguments.
     */
    override fun getArguments(elementContext: ElementReference?): MutableList<Any> {
        return atom.getArguments(elementContext).also { lastArgs = it }
    }

    /**
     * Calls interceptors before [Atom.transform] on wrapped [atom] is called.
     *
     * @param evaluation represents the results of a Javascript execution.
     * @return [R] a result type of the atom.
     */
    override fun transform(evaluation: Evaluation?): R {
        interceptors.forEach { it.intercept(evaluation, atom, lastArgs) }
        return atom.transform(evaluation)
    }

    /**
     * Simply calls [Atom.getScript] on wrapped [atom].
     *
     * @return the script to be evaluated.
     */
    override fun getScript(): String = atom.script
}