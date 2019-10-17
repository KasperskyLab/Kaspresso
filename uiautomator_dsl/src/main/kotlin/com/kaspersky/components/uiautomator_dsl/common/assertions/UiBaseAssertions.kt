package com.kaspersky.components.uiautomator_dsl.common.assertions

import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat

interface UiBaseAssertions {
    val assertionsView: UiObject2?

    fun isDisplayed() {
        assertThat(assertionsView).isNotNull()
    }

    fun isNotDisplayed() {
        assertThat(assertionsView).isNull()
    }

    fun isSelected() {
        assertThat(assertionsView?.isSelected).isTrue()
    }

    fun isNotSelected() {
        assertThat(assertionsView?.isSelected).isFalse()
    }

    fun isFocused() {
        assertThat(assertionsView?.isFocused).isTrue()
    }

    fun isNotFocused() {
        assertThat(assertionsView?.isFocused).isFalse()
    }

    fun isFocusable() {
        assertThat(assertionsView?.isFocusable).isTrue()
    }

    fun isNotFocusable() {
        assertThat(assertionsView?.isFocusable).isFalse()
    }

    fun isClickable() {
        assertThat(assertionsView?.isClickable).isTrue()
    }

    fun isNotClicable() {
        assertThat(assertionsView?.isClickable).isFalse()
    }

    fun isEnabled() {
        assertThat(assertionsView?.isEnabled).isTrue()
    }

    fun isDisabled() {
        assertThat(assertionsView?.isEnabled).isFalse()
    }
}