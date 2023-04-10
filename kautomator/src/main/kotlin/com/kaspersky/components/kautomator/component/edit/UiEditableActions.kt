@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.edit

import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides editable actions for UiEditText
 */
interface UiEditableActions : UiBaseActions {

    /**
     * Types the given text into the view
     *
     * @param text Text to input
     */
    fun typeText(text: String) {
        view.perform(
            UiEditableActionType.TYPE_TEXT,
            "typeText(text=$text)"
        ) { this.text = (this.text ?: "") + text }
    }

    /**
     * Replaces the current view text with given
     *
     * @param text Text to input instead of current
     */
    fun replaceText(text: String) {
        view.perform(
            UiEditableActionType.REPLACE_TEXT,
            "replaceText(text=$text)"
        ) { this.text = text }
    }

    /**
     * Clears the text content into the view
     */
    fun clearText() {
        view.perform(UiEditableActionType.CLEAR_TEXT) { clear() }
    }

    enum class UiEditableActionType : UiOperationType {
        TYPE_TEXT,
        REPLACE_TEXT,
        CLEAR_TEXT
    }
}
