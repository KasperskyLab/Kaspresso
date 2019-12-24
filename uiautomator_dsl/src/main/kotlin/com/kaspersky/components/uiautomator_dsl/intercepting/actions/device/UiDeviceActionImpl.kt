package com.kaspersky.components.uiautomator_dsl.intercepting.actions.device

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction

class UiDeviceActionImpl(
    private val type: UiDeviceActionType,
    private val description: String,
    private val action: UiDevice.() -> Unit
) : UiDeviceAction {

    override fun getType(): UiDeviceActionType = type

    override fun getDescription(): String = description

    override fun perform(interaction: UiDeviceInteraction) = action.invoke(interaction.device)
}