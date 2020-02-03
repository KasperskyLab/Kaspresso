package com.kaspersky.components.kautomator.dsl.common.assertions

import androidx.test.uiautomator.UiObject2
import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.kautomator.intercepting.operation.UiOperation
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

/**
 * Special separate Assertion to determine UiAutomator View is displayed or not
 */
class DisplayedObjectAssertion private constructor(
    override val type: DisplayedAssertionType
) : UiOperation<UiObject2> {

    companion object {
        fun assertDisplayed() = DisplayedObjectAssertion(DisplayedAssertionType.IS_DISPLAYED)

        @Deprecated("It does not work for now. This assertion will throw UnfoundedObjectException")
        fun assertNotDisplayed() = DisplayedObjectAssertion(DisplayedAssertionType.IS_NOT_DISPLAYED)
    }

    override val description: String? = null

    override fun execute(innerView: UiObject2) {
        when (type) {
            DisplayedAssertionType.IS_DISPLAYED -> assertThat(innerView).isNotNull()
            /**
             * This check will never fail because [innerView] is not nullable
             */
            DisplayedAssertionType.IS_NOT_DISPLAYED -> assertThat(innerView).isNull()
        }
    }

    override fun toString(): String {
        return "DisplayedObjectAssertion(type=$type, description=$description)"
    }

    enum class DisplayedAssertionType : UiOperationType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
    }
}