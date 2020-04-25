package com.kaspersky.kaspresso.proxy

import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.model.ElementReference
import androidx.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import org.hamcrest.Matcher

/**
 * The proxy-wrapper of [Atom] for watcher interceptors calls.
 */
class AtomProxy<T>(
    val atom: Atom<T>,
    val matcher: Matcher<*>,
    private val watcherInterceptors: List<AtomWatcherInterceptor>
) : Atom<T> {

    /**
     * Simply calls [Atom.getArguments] on wrapped [atom].
     *
     * @param elementContext null unless an ElementReference has been supplied to interact this atom with.
     * @return a [List] of objects to pass to the script as arguments.
     */
    override fun getArguments(elementContext: ElementReference?): MutableList<Any> {
        return atom.getArguments(elementContext)
    }

    /**
     * Calls watcher interceptors before [Atom.transform] on wrapped [atom] is called.
     *
     * @param evaluation represents the results of a Javascript execution.
     * @return [R] a result type of the atom.
     */
    override fun transform(evaluation: Evaluation?): T {
        watcherInterceptors.forEach { it.intercept(this, evaluation) }
        return atom.transform(evaluation)
    }

    /**
     * Simply calls [Atom.getScript] on wrapped [atom].
     *
     * @return the script to be evaluated.
     */
    override fun getScript(): String = atom.script
}
