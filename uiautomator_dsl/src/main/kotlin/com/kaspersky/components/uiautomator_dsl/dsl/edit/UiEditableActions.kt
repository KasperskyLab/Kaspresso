package com.kaspersky.components.uiautomator_dsl.dsl.edit

import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions

interface UiEditableActions : UiBaseActions {

    fun typeText(text: String) {
        actionsView.perform("typeText(text=$text)") { this.text = this.text + text }
    }

    fun replaceText(text: String) {
        actionsView.perform("replaceText(text=$text)") { this.text = text }
    }
}