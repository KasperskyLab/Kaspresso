package com.kaspersky.components.uiautomator_dsl.dsl.edit

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

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
            EditableUiActionType.TYPE_TEXT,
            "typeText(text=$text)"
        ) { this.text = this.text + text }
    }

    /**
     * Replaces the current view text with given
     *
     * @param text Text to input instead of current
     */
    fun replaceText(text: String) {
        view.perform(
            EditableUiActionType.REPLACE_TEXT,
            "replaceText(text=$text)"
        ) { this.text = text }
    }

    /**
     * Clears the text content into the view
     */
    fun clearText() {
        view.perform(EditableUiActionType.CLEAR_TEXT) { clear() }
    }

    enum class EditableUiActionType : UiActionType {
        TYPE_TEXT,
        REPLACE_TEXT,
        CLEAR_TEXT
    }
}