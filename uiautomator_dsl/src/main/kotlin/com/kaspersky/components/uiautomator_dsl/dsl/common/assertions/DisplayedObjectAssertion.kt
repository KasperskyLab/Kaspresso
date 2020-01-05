package com.kaspersky.components.uiautomator_dsl.dsl.common.assertions

import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

/**
 * Special separate Assertion to determine UiAutomator View is displayed or not
 */
class DisplayedObjectAssertion private constructor(
    private val type: DisplayedAssertType
) : UiAction<UiObject2> {

    companion object {
        fun assertDisplayed() = DisplayedObjectAssertion(DisplayedAssertType.IS_DISPLAYED)
        fun assertNotDisplayed() = DisplayedObjectAssertion(DisplayedAssertType.IS_NOT_DISPLAYED)
    }

    override fun getType(): UiActionType = type

    override fun getDescription(): String? = null

    override fun execute(innerView: UiObject2) {
        when (type) {
            DisplayedAssertType.IS_DISPLAYED -> assertThat(innerView).isNotNull()
            DisplayedAssertType.IS_NOT_DISPLAYED -> assertThat(innerView).isNull()
        }
    }

    enum class DisplayedAssertType : UiActionType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
    }
}