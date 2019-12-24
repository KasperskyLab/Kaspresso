package com.kaspersky.components.uiautomator_dsl.intercepting.asserts

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.exceptions.UnfoundedUiObjectException

class UiAssertImpl(
    private val type: UiAssertType,
    private val description: String,
    private val assert: UiObject2.() -> Unit
) : UiAssert {

    override fun getType(): UiAssertType = type

    override fun getDescription(): String = description

    override fun check(interaction: UiInteraction) {
        val uiObject2 = interaction.uiObject2
            ?: throw UnfoundedUiObjectException(
                interaction.selector
            )
        assert.invoke(uiObject2)
    }
}