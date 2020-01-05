package com.kaspersky.components.uiautomator_dsl.dsl.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

/**
 * Provides assertions for UiTextView
 */
interface UiTextViewAssertions : UiBaseAssertions {

    /**
     * Checks if the view has empty text
     */
    fun hasEmptyText() {
        view.check(TextViewAssertType.HAS_EMPTY_TEXT) { assertThat(text).isEmpty() }
    }

    /**
     * Checks if the view has any text
     */
    fun hasAnyText() {
        view.check(TextViewAssertType.HAS_ANY_TEXT) { assertThat(text).isNotEmpty() }
    }

    /**
     * Checks if the view has concrete text
     */
    fun hasText(text: String) {
        view.check(
            TextViewAssertType.HAS_TEXT,
            "hasText(text=$text)"
        // todo isEqualTo doesn't support auto-capitalization
        // todo but Kakao does
        ) { assertThat(this.text).isEqualTo(text) }
    }

    /**
     * Checks if the view has not concrete text
     */
    fun hasNoText(text: String) {
        view.check(
            TextViewAssertType.HAS_NO_TEXT,
            "hasNoText(text=$text)"
        ) { assertThat(this.text).isNotEqualTo(text) }
    }

    /**
     * Checks if the view contains concrete text
     */
    fun containsText(text: String) {
        view.check(
            TextViewAssertType.CONTAINS_TEXT,
            "containsText(text=$text)"
        ) { assertThat(this.text).contains(text) }
    }

    enum class TextViewAssertType : UiActionType {
        HAS_EMPTY_TEXT,
        HAS_ANY_TEXT,
        HAS_TEXT,
        HAS_NO_TEXT,
        CONTAINS_TEXT
    }
}