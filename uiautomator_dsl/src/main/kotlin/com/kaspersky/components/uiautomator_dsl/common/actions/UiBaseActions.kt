package com.kaspersky.components.uiautomator_dsl.common.actions

import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat

interface UiBaseActions {
    val actionsView: UiObject2?

    fun click() {
        assertThat(actionsView).isNotNull()
        actionsView?.click()
    }

    fun doubleClick() {
        assertThat(actionsView).isNotNull()
        actionsView?.click()
        actionsView?.click()
    }

    fun longClick() {
        assertThat(actionsView).isNotNull()
        actionsView?.longClick()
    }
}