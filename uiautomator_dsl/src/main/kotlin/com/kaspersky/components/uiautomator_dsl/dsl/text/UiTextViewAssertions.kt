package com.kaspersky.components.uiautomator_dsl.dsl.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions.TextViewAssertType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType

/**
 * Provides assertions for UiTextView
 */
interface UiTextViewAssertions : UiBaseAssertions {

    /**
     * Checks if the view has empty text
     */
    fun hasEmptyText() {
        assertionsView.check(HAS_EMPTY_TEXT) { assertThat(text).isEmpty() }
    }

    /**
     * Checks if the view has any text
     */
    fun hasAnyText() {
        assertionsView.check(HAS_ANY_TEXT) { assertThat(text).isNotEmpty() }
    }

    /**
     * Checks if the view has concrete text
     */
    fun hasText(text: String) {
        assertionsView.check(
            HAS_TEXT,
            "hasText(text=$text)"
        ) { assertThat(this.text).isEqualTo(text) }
    }

    /**
     * Checks if the view has not concrete text
     */
    fun hasNoText(text: String) {
        assertionsView.check(
            HAS_NO_TEXT,
            "hasNoText(text=$text)"
        ) { assertThat(this.text).isNotEqualTo(text) }
    }

    /**
     * Checks if the view contains concrete text
     */
    fun containsText(text: String) {
        assertionsView.check(
            CONTAINS_TEXT,
            "containsText(text=$text)"
        ) { assertThat(this.text).contains(text) }
    }

    enum class TextViewAssertType : UiOperationType {
        HAS_EMPTY_TEXT,
        HAS_ANY_TEXT,
        HAS_TEXT,
        HAS_NO_TEXT,
        CONTAINS_TEXT
    }
}