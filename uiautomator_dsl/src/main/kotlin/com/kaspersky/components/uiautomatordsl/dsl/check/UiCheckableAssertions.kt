package com.kaspersky.components.uiautomatordsl.dsl.check

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomatordsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiOperationType

/**
 * Provides assertions for UiCheckBox
 */
interface UiCheckableAssertions : UiBaseAssertions {

    /**
     * Checks if the view is checked
     */
    fun isChecked() {
        view.check(CheckableAssertionType.IS_CHECKED) { assertThat(isChecked).isTrue() }
    }

    /**
     * Checks if the view is not checked
     */
    fun isNotChecked() {
        view.check(CheckableAssertionType.IS_NOT_CHECKED) { assertThat(isChecked).isFalse() }
    }

    /**
     * Checks if the view is checkable
     */
    fun isCheckable() {
        view.check(CheckableAssertionType.IS_CHECKABLE) { assertThat(isCheckable).isTrue() }
    }

    /**
     * Checks if the view is not checkable
     */
    fun isNotCheckable() {
        view.check(CheckableAssertionType.IS_NOT_CHECKABLE) { assertThat(isCheckable).isFalse() }
    }

    enum class CheckableAssertionType : UiOperationType {
        IS_CHECKED,
        IS_NOT_CHECKED,
        IS_CHECKABLE,
        IS_NOT_CHECKABLE
    }
}