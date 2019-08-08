package com.kaspersky.kaspresso.proxy

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.view.AtomInterceptor
import org.hamcrest.Matcher

/**
 * A proxy-wrapper of [Atom] for interceptors calls.
 */
class AtomProxy<R>(
    val atom: Atom<R>,
    val matcher: Matcher<*>,
    private val interceptors: List<AtomInterceptor>
) : Atom<R> {

    /**
     * Simply calls [Atom.getArguments] on wrapped [atom].
     *
     * @param elementContext null unless an ElementReference has been supplied to execute this atom with.
     * @return a [List] of objects to pass to the script as arguments.
     */
    override fun getArguments(elementContext: ElementReference?): MutableList<Any> {
        return atom.getArguments(elementContext)
    }

    /**
     * Calls interceptors before [Atom.transform] on wrapped [atom] is called.
     *
     * @param evaluation represents the results of a Javascript execution.
     * @return [R] a result type of the atom.
     */
    override fun transform(evaluation: Evaluation?): R {
        interceptors.forEach { it.intercept(this, evaluation) }
        return atom.transform(evaluation)
    }

    /**
     * Simply calls [Atom.getScript] on wrapped [atom].
     *
     * @return the script to be evaluated.
     */
    override fun getScript(): String = atom.script
}