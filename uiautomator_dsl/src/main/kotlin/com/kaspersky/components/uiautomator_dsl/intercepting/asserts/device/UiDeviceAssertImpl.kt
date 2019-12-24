package com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction

class UiDeviceAssertImpl(
    private val type: UiDeviceAssertType,
    private val description: String,
    private val action: UiDevice.() -> Unit
) : UiDeviceAssert {

    override fun getType(): UiDeviceAssertType = type

    override fun getDescription(): String = description

    override fun check(interaction: UiDeviceInteraction) = action.invoke(interaction.device)
}