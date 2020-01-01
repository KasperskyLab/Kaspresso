package com.kaspersky.components.uiautomator_dsl.dsl.check

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.dsl.check.UiCheckableAssertions.CheckableAssertType.*

/**
 * Provides assertions for UiCheckBox
 */
interface UiCheckableAssertions : UiBaseAssertions {

    /**
     * Checks if the view is checked
     */
    fun isChecked() {
        view.check(IS_CHECKED) { assertThat(isChecked).isTrue() }
    }

    /**
     * Checks if the view is not checked
     */
    fun isNotChecked() {
        view.check(IS_NOT_CHECKED) { assertThat(isChecked).isFalse() }
    }

    /**
     * Checks if the view is checkable
     */
    fun isCheckable() {
        view.check(IS_CHECKABLE) { assertThat(isCheckable).isTrue() }
    }

    /**
     * Checks if the view is not checkable
     */
    fun isNotCheckable() {
        view.check(IS_NOT_CHECKABLE) { assertThat(isCheckable).isFalse() }
    }

    enum class CheckableAssertType : UiActionType {
        IS_CHECKED,
        IS_NOT_CHECKED,
        IS_CHECKABLE,
        IS_NOT_CHECKABLE
    }

}