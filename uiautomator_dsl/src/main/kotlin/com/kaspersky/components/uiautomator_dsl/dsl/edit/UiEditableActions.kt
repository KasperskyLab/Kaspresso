package com.kaspersky.components.uiautomator_dsl.dsl.edit

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditableActions.EditableUiOperationType.*

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
        actionsView.perform(
            TYPE_TEXT,
            "typeText(text=$text)"
        ) { this.text = this.text + text }
    }

    /**
     * Replaces the current view text with given
     *
     * @param text Text to input instead of current
     */
    fun replaceText(text: String) {
        actionsView.perform(
            REPLACE_TEXT,
            "replaceText(text=$text)"
        ) { this.text = text }
    }

    enum class EditableUiOperationType : UiOperationType {
        TYPE_TEXT,
        REPLACE_TEXT
    }
}