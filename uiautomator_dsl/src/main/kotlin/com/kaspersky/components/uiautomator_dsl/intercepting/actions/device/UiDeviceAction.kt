package com.kaspersky.components.uiautomator_dsl.intercepting.actions.device

import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device.UiDeviceAssertType
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction

interface UiDeviceAction {

    fun getType(): UiDeviceActionType

    fun getDescription(): String

    fun perform(interaction: UiDeviceInteraction)
}