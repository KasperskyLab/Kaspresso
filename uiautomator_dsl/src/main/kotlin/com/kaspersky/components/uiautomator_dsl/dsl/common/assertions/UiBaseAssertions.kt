package com.kaspersky.components.uiautomator_dsl.dsl.common.assertions

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate
import com.kaspersky.components.uiautomator_dsl.dsl.common.assertions.UiBaseAssertions.BaseAssertType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType

/**
 * Base interface for asserting UiAutomator views
 *
 * Provides basic assertions that can be performed on any view
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions
 */
interface UiBaseAssertions {
    val assertionsView: UiObjectDelegate

    /**
     * Checks if the view is completely displayed
     */
    fun isDisplayed() {
        assertionsView.check(DisplayedObjectAssertion.assertDisplayed())
    }

    /**
     * Checks if the view is not completely displayed
     */
    fun isNotDisplayed() {
        assertionsView.check(DisplayedObjectAssertion.assertNotDisplayed())
    }

    /**
     * Checks if the view is selected
     */
    fun isSelected() {
        assertionsView.check(IS_SELECTED) { assertThat(isSelected).isTrue() }
    }

    /**
     * Checks if the view is not selected
     */
    fun isNotSelected() {
        assertionsView.check(IS_NOT_SELECTED) { assertThat(isSelected).isFalse() }
    }

    /**
     * Checks if the view is focused
     */
    fun isFocused() {
        assertionsView.check(IS_FOCUSED) { assertThat(isFocused).isTrue() }
    }

    /**
     * Checks if the view is not focused
     */
    fun isNotFocused() {
        assertionsView.check(IS_NOT_FOCUSED) { assertThat(isFocused).isFalse() }
    }

    /**
     * Checks if the view is focusable
     */
    fun isFocusable() {
        assertionsView.check(IS_FOCUSABLE) { assertThat(isFocusable).isTrue() }
    }

    /**
     * Checks if the view is not focusable
     */
    fun isNotFocusable() {
        assertionsView.check(IS_NOT_FOCUSABLE) { assertThat(isFocusable).isFalse() }
    }

    /**
     * Checks if the view is clickable
     */
    fun isClickable() {
        assertionsView.check(IS_CLICKABLE) { assertThat(isClickable).isTrue() }
    }

    /**
     * Checks if the view is not clickable
     */
    fun isNotClickable() {
        assertionsView.check(IS_NOT_CLICKABLE) { assertThat(isClickable).isFalse() }
    }

    /**
     * Checks if the view is enabled
     */
    fun isEnabled() {
        assertionsView.check(IS_ENABLED) { assertThat(isEnabled).isTrue() }
    }

    /**
     * Checks if the view is disabled
     */
    fun isDisabled() {
        assertionsView.check(IS_DISABLED) { assertThat(isEnabled).isFalse() }
    }

    enum class BaseAssertType : UiOperationType {
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