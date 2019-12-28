package com.kaspersky.components.uiautomator_dsl.dsl.common.assertions

import com.google.common.truth.Truth.assertThat
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperation
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction

/**
 * Special separate Assertion to determine UiAutomator View is displayed or not
 */
class DisplayedObjectAssertion private constructor(
    private val type: DisplayedAssertType
) : UiOperation<UiObjectInteraction> {

    companion object {
        fun assertDisplayed() = DisplayedObjectAssertion(DisplayedAssertType.IS_DISPLAYED)
        fun assertNotDisplayed() = DisplayedObjectAssertion(DisplayedAssertType.IS_NOT_DISPLAYED)
    }

    override fun getType(): UiOperationType = type

    override fun getDescription(): String? = null

    override fun execute(interaction: UiObjectInteraction) {
        val uiObject = interaction.uiObject2
        when(type) {
            DisplayedAssertType.IS_DISPLAYED -> assertThat(uiObject).isNotNull()
            DisplayedAssertType.IS_NOT_DISPLAYED -> assertThat(uiObject).isNull()
        }
    }

    enum class DisplayedAssertType : UiOperationType {
        IS_DISPLAYED,
        IS_NOT_DISPLAYED,
    }

}