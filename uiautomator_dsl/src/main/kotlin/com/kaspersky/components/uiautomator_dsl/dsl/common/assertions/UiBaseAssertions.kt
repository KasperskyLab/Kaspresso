package com.kaspersky.components.uiautomator_dsl.dsl.common.assertions

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate

/**
 * Base interface for asserting UiAutomator views
 *
 * Provides basic assertions that can be performed on any view
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.text.UiTextViewAssertions
 */
interface UiBaseAssertions {
    val view: UiObjectDelegate

    /**
     * Checks if the view is completely displayed
     */
    fun isDisplayed() {
        view.check(DisplayedObjectAssertion.assertDisplayed())
    }

    /**
     * Checks if the view is not completely displayed
     */
    fun isNotDisplayed() {
        view.check(DisplayedObjectAssertion.assertNotDisplayed())
    }

    /**
     * Checks if the view is selected
     */
    fun isSelected() {
        view.check(BaseAssertType.IS_SELECTED) { assertThat(isSelected).isTrue() }
    }

    /**
     * Checks if the view is not selected
     */
    fun isNotSelected() {
        view.check(BaseAssertType.IS_NOT_SELECTED) { assertThat(isSelected).isFalse() }
    }

    /**
     * Checks if the view is focused
     */
    fun isFocused() {
        view.check(BaseAssertType.IS_FOCUSED) { assertThat(isFocused).isTrue() }
    }

    /**
     * Checks if the view is not focused
     */
    fun isNotFocused() {
        view.check(BaseAssertType.IS_NOT_FOCUSED) { assertThat(isFocused).isFalse() }
    }

    /**
     * Checks if the view is focusable
     */
    fun isFocusable() {
        view.check(BaseAssertType.IS_FOCUSABLE) { assertThat(isFocusable).isTrue() }
    }

    /**
     * Checks if the view is not focusable
     */
    fun isNotFocusable() {
        view.check(BaseAssertType.IS_NOT_FOCUSABLE) { assertThat(isFocusable).isFalse() }
    }

    /**
     * Checks if the view is clickable
     */
    fun isClickable() {
        view.check(BaseAssertType.IS_CLICKABLE) { assertThat(isClickable).isTrue() }
    }

    /**
     * Checks if the view is not clickable
     */
    fun isNotClickable() {
        view.check(BaseAssertType.IS_NOT_CLICKABLE) { assertThat(isClickable).isFalse() }
    }

    /**
     * Checks if the view is enabled
     */
    fun isEnabled() {
        view.check(BaseAssertType.IS_ENABLED) { assertThat(isEnabled).isTrue() }
    }

    /**
     * Checks if the view is disabled
     */
    fun isDisabled() {
        view.check(BaseAssertType.IS_DISABLED) { assertThat(isEnabled).isFalse() }
    }

    enum class BaseAssertType : UiActionType {
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