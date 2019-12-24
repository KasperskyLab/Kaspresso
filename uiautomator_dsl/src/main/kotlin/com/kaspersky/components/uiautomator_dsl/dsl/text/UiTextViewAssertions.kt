package com.kaspersky.components.uiautomator_dsl.dsl.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssertType
import com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions.TextViewAssertType.*

interface UiTextViewAssertions : UiBaseAssertions {

    fun hasEmptyText() {
        assertionsView.check(HAS_EMPTY_TEXT) { assertThat(text).isEmpty() }
    }

    fun hasAnyText() {
        assertionsView.check(HAS_ANY_TEXT) { assertThat(text).isNotEmpty() }
    }

    fun hasText(text: String) {
        assertionsView.check(
            HAS_TEXT,
            "hasText(text=$text)"
        ) { assertThat(this.text).isEqualTo(text) }
    }

    fun hasNoText(text: String) {
        assertionsView.check(
            HAS_NO_TEXT,
            "hasNoText(text=$text)"
        ) { assertThat(this.text).isNotEqualTo(text) }
    }

    fun containsText(text: String) {
        assertionsView.check(
            CONTAINS_TEXT,
            "containsText(text=$text)"
        ) { assertThat(this.text).contains(text) }
    }

    enum class TextViewAssertType : UiAssertType {
        HAS_EMPTY_TEXT,
        HAS_ANY_TEXT,
        HAS_TEXT,
        HAS_NO_TEXT,
        CONTAINS_TEXT
    }
}