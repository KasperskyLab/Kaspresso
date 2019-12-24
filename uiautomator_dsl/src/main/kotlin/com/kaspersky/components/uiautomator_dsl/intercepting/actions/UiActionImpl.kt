package com.kaspersky.components.uiautomator_dsl.intercepting.actions

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.exceptions.UnfoundedUiObjectException

class UiActionImpl(
    private val type: UiActionType,
    private val description: String,
    private val action: UiObject2.() -> Unit
) : UiAction {

    override fun getType(): UiActionType = type

    override fun getDescription(): String = description

    override fun perform(objectInteraction: UiObjectInteraction) {
        val uiObject2 = objectInteraction.uiObject2
            ?: throw UnfoundedUiObjectException(
                objectInteraction.selector
            )
        action.invoke(uiObject2)
    }
}