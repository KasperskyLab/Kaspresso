package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction

/**
 * Responsible for executing an interaction on the given UiDevice element
 */
class UiDeviceOperation(
    private val type: UiOperationType,
    private val description: String?,
    private val action: UiDevice.() -> Unit
) : UiOperation<UiDeviceInteraction> {

    override fun getType(): UiOperationType = type

    override fun getDescription(): String? = description

    override fun execute(interaction: UiDeviceInteraction) = action.invoke(interaction.device)
}