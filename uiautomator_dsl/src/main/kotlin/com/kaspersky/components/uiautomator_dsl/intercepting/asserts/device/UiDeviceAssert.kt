package com.kaspersky.components.uiautomator_dsl.intercepting.asserts.device

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction

interface UiDeviceAssert {

    fun getType(): UiDeviceAssertType

    fun getDescription(): String

    fun check(interaction: UiDeviceInteraction)
}