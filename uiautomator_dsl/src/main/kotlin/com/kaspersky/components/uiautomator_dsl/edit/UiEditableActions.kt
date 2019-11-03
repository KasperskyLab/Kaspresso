package com.kaspersky.components.uiautomator_dsl.edit

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.common.actions.UiBaseActions

interface UiEditableActions : UiBaseActions {

    fun typeText(text: String) {
        assertThat(actionsView).isNotNull()
        actionsView?.text = actionsView?.text + text
    }

    fun replaceText(text: String) {
        assertThat(actionsView).isNotNull()
        actionsView?.text = text
    }
}