package com.kaspersky.components.uiautomator_dsl.intercepting.actions

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.exceptions.UnfoundedUiObjectException

class UiActionImpl(
    private val description: String,
    private val action: UiObject2.() -> Unit
) : UiAction {

    override fun getDescription(): String = description

    override fun perform(interaction: UiInteraction) {
        val uiObject2 = interaction.uiObject2
            ?: throw UnfoundedUiObjectException(
                interaction.selector
            )
        action.invoke(uiObject2)
    }
}