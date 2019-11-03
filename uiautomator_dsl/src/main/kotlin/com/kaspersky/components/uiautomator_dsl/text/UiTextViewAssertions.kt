package com.kaspersky.components.uiautomator_dsl.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.common.assertions.UiBaseAssertions

interface UiTextViewAssertions : UiBaseAssertions {

    fun hasEmptyText() {
        assertThat(assertionsView).isNotNull()
        assertThat(assertionsView?.text).isEmpty()
    }

    fun hasAnyText() {
        assertThat(assertionsView).isNotNull()
        assertThat(assertionsView?.text).isNotEmpty()
    }

    fun hasText(text: String) {
        assertThat(assertionsView).isNotNull()
        assertThat(assertionsView?.text).isEqualTo(text)
    }

    fun hasNoText(text: String) {
        assertThat(assertionsView).isNotNull()
        assertThat(assertionsView?.text).isNotEqualTo(text)
    }

    fun containsText(text: String) {
        assertThat(assertionsView).isNotNull()
        assertThat(assertionsView?.text).contains(text)
    }
}