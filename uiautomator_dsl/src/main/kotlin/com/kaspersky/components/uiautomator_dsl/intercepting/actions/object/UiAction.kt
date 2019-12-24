package com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction

interface UiAction {

    fun getType(): UiActionType

    fun getDescription(): String

    fun perform(interaction: UiInteraction)

}