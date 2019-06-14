package com.kaspersky.kaspresso.interceptors.impl.logging

import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.Evaluation
import android.support.test.espresso.web.webdriver.WebDriverAtomScriptsProvider
import com.kaspersky.kaspresso.interceptors.AtomInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of [AtomInterceptor] that logs info about web action.
 */
class LoggingAtomInterceptor(
    private val logger: UiTestLogger
) : AtomInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    override fun <R> intercept(
        evaluation: Evaluation?,
        atom: Atom<R>,
        lastArgs: MutableList<Any>?
    ) {
        val evalMessage: String = with(evaluation) {
            if (this != null && hasMessage()) " with message=\"$message\"" else ""
        }

        if (isFindElementAction(atom)) {
            logger.startI("On web element with args=${lastArgs?.let { it } ?: "null"} perform$evalMessage")
        } else {
            logger.finishI("web action: ${getActionDescription(atom)}$evalMessage")
        }
    }

    private fun <R> isFindElementAction(atom: Atom<R>): Boolean {
        with(WebDriverAtomScriptsProvider) {
            return atom.script == FIND_ELEMENT_ANDROID || atom.script == FIND_ELEMENTS_ANDROID
        }
    }

    private fun <R> getActionDescription(atom: Atom<R>): String {
        return with(WebDriverAtomScriptsProvider) {
            when (atom.script) {
                CLEAR_ANDROID -> "clear"
                CLICK_ANDROID -> "click on element"
                SCROLL_INTO_VIEW_ANDROID -> "scroll into view"
                SEND_KEYS_ANDROID -> "send keys"
                ACTIVE_ELEMENT_ANDROID -> "active element"
                FRAME_BY_ID_OR_NAME_ANDROID -> "frame by id or name"
                FRAME_BY_INDEX_ANDROID -> "frame by index android"
                GET_VISIBLE_TEXT_ANDROID -> "get visible text"
                FIND_ELEMENT_ANDROID -> "find element"
                FIND_ELEMENTS_ANDROID -> "find elements"
                else -> "unknown action"
            }
        }
    }
}