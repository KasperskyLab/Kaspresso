package com.kaspersky.components.uiautomator_dsl.intercepting.asserts

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction

interface UiAssert {

    fun getType(): UiAssertType

    fun getDescription(): String

    fun check(interaction: UiInteraction)

}