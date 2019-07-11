package com.kaspersky.kaspresso.proxy

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.model.Evaluation
import android.support.test.espresso.web.webdriver.WebDriverAtomScriptsProvider
import com.kaspersky.kaspresso.interceptors.view.AtomInterceptor
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

/**
 * A proxy-wrapper of [Atom] for interceptors calls.
 */
class AtomProxy<R>(
    private val atom: Atom<R>,
    private val matcher: Matcher<*>,
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

    /**
     * @return a string description of [Atom].
     */
    fun describe(): String {
        val builder = StringBuilder("web action \"${atom.getActionDescription()}\" on webview ")
        matcher.describeTo(StringDescription(builder))
        return builder.toString()
    }

    private fun Atom<*>.getActionDescription(): String {
        return with(WebDriverAtomScriptsProvider) {
            when (script) {
                GET_VISIBLE_TEXT_ANDROID -> "get visible text"
                CLICK_ANDROID -> "click on element"
                SCROLL_INTO_VIEW_ANDROID -> "scroll into view"
                CLEAR_ANDROID -> "clear"
                SEND_KEYS_ANDROID -> "send keys"
                ACTIVE_ELEMENT_ANDROID -> "active element"
                FRAME_BY_ID_OR_NAME_ANDROID -> "frame by id or name"
                FRAME_BY_INDEX_ANDROID -> "frame by index android"
                FIND_ELEMENT_ANDROID -> "find element"
                FIND_ELEMENTS_ANDROID -> "find elements"
                else -> ""
            }
        }
    }
}