package com.kaspersky.components.uiautomator_dsl.dsl.edit

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`.UiActionType
import com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditableActions.EditableActionType.*

interface UiEditableActions : UiBaseActions {

    fun typeText(text: String) {
        actionsView.perform(
            TYPE_TEXT,
            "typeText(text=$text)"
        ) { this.text = this.text + text }
    }

    fun replaceText(text: String) {
        actionsView.perform(
            REPLACE_TEXT,
            "replaceText(text=$text)"
        ) { this.text = text }
    }

    enum class EditableActionType : UiActionType {
        TYPE_TEXT,
        REPLACE_TEXT
    }
}