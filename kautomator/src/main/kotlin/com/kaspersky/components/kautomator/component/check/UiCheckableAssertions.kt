@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.check

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides assertions for checkable views
 */
interface UiCheckableAssertions : UiBaseAssertions {

    /**
     * Checks if the view is checked
     */
    fun isChecked() {
        view.check(UiCheckableAssertionType.IS_CHECKED) { assertThat(isChecked).isTrue() }
    }

    /**
     * Checks if the view is not checked
     */
    fun isNotChecked() {
        view.check(UiCheckableAssertionType.IS_NOT_CHECKED) { assertThat(isChecked).isFalse() }
    }

    enum class UiCheckableAssertionType : UiOperationType {
        IS_CHECKED,
        IS_NOT_CHECKED
    }
}
