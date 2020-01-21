@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.check

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

/**
 * Provides assertions for checkable views
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

    enum class CheckableAssertionType : UiOperationType {
        IS_CHECKED,
        IS_NOT_CHECKED
    }
}