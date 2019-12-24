package com.kaspersky.components.uiautomator_dsl.intercepting.actions

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction

interface UiAction {

    fun getType(): UiActionType

    fun getDescription(): String

    fun perform(objectInteraction: UiObjectInteraction)

}