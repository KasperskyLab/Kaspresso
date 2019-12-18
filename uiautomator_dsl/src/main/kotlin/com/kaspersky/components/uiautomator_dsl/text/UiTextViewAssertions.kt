package com.kaspersky.components.uiautomator_dsl.text

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.common.assertions.UiBaseAssertions

interface UiTextViewAssertions : UiBaseAssertions {

    fun hasEmptyText() {
        assertionsView.check("hasEmptyText") { assertThat(text).isEmpty() }
    }

    fun hasAnyText() {
        assertionsView.check("hasAnyText") { assertThat(text).isNotEmpty() }
    }

    fun hasText(text: String) {
        assertionsView.check("hasText(text=$text)") { assertThat(this.text).isEqualTo(text) }
    }

    fun hasNoText(text: String) {
        assertionsView.check("hasNoText(text=$text)") { assertThat(this.text).isNotEqualTo(text) }
    }

    fun containsText(text: String) {
        assertionsView.check("containsText(text=$text)") { assertThat(this.text).contains(text) }
    }
}