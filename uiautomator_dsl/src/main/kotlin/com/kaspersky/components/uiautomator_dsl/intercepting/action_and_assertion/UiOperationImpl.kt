package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.exception.UnfoundedUiObjectException

class UiOperationImpl(
    private val type: UiOperationType,
    private val description: String?,
    private val action: UiObject2.() -> Unit
) : UiOperation<UiInteraction> {

    override fun getType(): UiOperationType = type

    override fun getDescription(): String? = description

    override fun execute(interaction: UiInteraction) {
        val uiObject2 = interaction.uiObject2
            ?: throw UnfoundedUiObjectException(
                interaction.selector
            )
        action.invoke(uiObject2)
    }
}