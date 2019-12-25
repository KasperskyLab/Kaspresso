package com.kaspersky.components.uiautomator_dsl.dsl.common.assertions

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiProxy
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions.BaseAssertType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiOperationType

interface UiBaseAssertions {
    val assertionsView: UiProxy

    fun isDisplayed() {
        assertionsView.check(IS_DISPLAYED) {
            // todo
            // assertThat(assertionsView).isNotNull()
        }
    }

    fun isNotDisplayed() {
        assertionsView.check(IS_NOT_DISPLAYED) {
            // todo
            // assertThat(assertionsView).isNull()
        }
    }

    fun isSelected() {
        assertionsView.check(IS_SELECTED) { assertThat(isSelected).isTrue() }
    }

    fun isNotSelected() {
        assertionsView.check(IS_NOT_SELECTED) { assertThat(isSelected).isFalse() }
    }

    fun isFocused() {
        assertionsView.check(IS_FOCUSED) { assertThat(isFocused).isTrue() }
    }

    fun isNotFocused() {
        assertionsView.check(IS_NOT_FOCUSED) { assertThat(isFocused).isFalse() }
    }

    fun isFocusable() {
        assertionsView.check(IS_FOCUSABLE) { assertThat(isFocusable).isTrue() }
    }

    fun isNotFocusable() {
        assertionsView.check(IS_NOT_FOCUSABLE) { assertThat(isFocusable).isFalse() }
    }

    fun isClickable() {
        assertionsView.check(IS_CLICKABLE) { assertThat(isClickable).isTrue() }
    }

    fun isNotClickable() {
        assertionsView.check(IS_NOT_CLICKABLE) { assertThat(isClickable).isFalse() }
    }

    fun isEnabled() {
        assertionsView.check(IS_ENABLED) { assertThat(isEnabled).isTrue() }
    }

    fun isDisabled() {
        assertionsView.check(IS_DISABLED) { assertThat(isEnabled).isFalse() }
    }

    enum class BaseAssertType : UiOperationType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
        IS_SELECTED,
        IS_NOT_SELECTED,
        IS_FOCUSED,
        IS_NOT_FOCUSED,
        IS_FOCUSABLE,
        IS_NOT_FOCUSABLE,
        IS_CLICKABLE,
        IS_NOT_CLICKABLE,
        IS_ENABLED,
        IS_DISABLED
    }
}