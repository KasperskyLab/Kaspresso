package com.kaspersky.components.uiautomator_dsl.common.assertions

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.proxy.UiObject2Proxy

interface UiBaseAssertions {
    val assertionsView: UiObject2Proxy

    fun isDisplayed() {
        assertionsView.check("isDisplayed") {
            // todo
            // assertThat(assertionsView).isNotNull()
        }
    }

    fun isNotDisplayed() {
        assertionsView.check("isNotDisplayed") {
            // todo
            // assertThat(assertionsView).isNull()
        }
    }

    fun isSelected() {
        assertionsView.check("isSelected") { assertThat(isSelected).isTrue() }
    }

    fun isNotSelected() {
        assertionsView.check("isNotSelected") { assertThat(isSelected).isFalse() }
    }

    fun isFocused() {
        assertionsView.check("isFocused") { assertThat(isFocused).isTrue() }
    }

    fun isNotFocused() {
        assertionsView.check("isNotFocused") { assertThat(isFocused).isFalse() }
    }

    fun isFocusable() {
        assertionsView.check("isFocusable") { assertThat(isFocusable).isTrue() }
    }

    fun isNotFocusable() {
        assertionsView.check("isNotFocusable") { assertThat(isFocusable).isFalse() }
    }

    fun isClickable() {
        assertionsView.check("isClickable") { assertThat(isClickable).isTrue() }
    }

    fun isNotClickable() {
        assertionsView.check("isNotClickable") { assertThat(isClickable).isFalse() }
    }

    fun isEnabled() {
        assertionsView.check("isEnabled") { assertThat(isEnabled).isTrue() }
    }

    fun isDisabled() {
        assertionsView.check("isDisabled") { assertThat(isEnabled).isFalse() }
    }
}