@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides assertions for UiTextView
 */
interface UiTextViewAssertions : UiBaseAssertions {

    /**
     * Checks if the view has empty text
     */
    fun hasEmptyText() {
        view.check(UiTextViewAssertionType.HAS_EMPTY_TEXT) { assertThat(text).isEmpty() }
    }

    /**
     * Checks if the view has any text
     */
    fun hasAnyText() {
        view.check(UiTextViewAssertionType.HAS_ANY_TEXT) { assertThat(text).isNotEmpty() }
    }

    /**
     * Checks if the view has concrete text
     */
    fun hasText(text: String) {
        view.check(
            UiTextViewAssertionType.HAS_TEXT,
            "hasText(text=$text)"
        ) { assertThat(this.text).isEqualTo(text) }
    }

    /**
     * Checks if the view has not concrete text
     */
    fun hasNoText(text: String) {
        view.check(
            UiTextViewAssertionType.HAS_NO_TEXT,
            "hasNoText(text=$text)"
        ) { assertThat(this.text).isNotEqualTo(text) }
    }

    /**
     * Checks if the view contains concrete text
     */
    fun containsText(text: String) {
        view.check(
            UiTextViewAssertionType.CONTAINS_TEXT,
            "containsText(text=$text)"
        ) { assertThat(this.text).contains(text) }
    }

    enum class UiTextViewAssertionType : UiOperationType {
        HAS_EMPTY_TEXT,
        HAS_ANY_TEXT,
        HAS_TEXT,
        HAS_NO_TEXT,
        CONTAINS_TEXT
    }
}