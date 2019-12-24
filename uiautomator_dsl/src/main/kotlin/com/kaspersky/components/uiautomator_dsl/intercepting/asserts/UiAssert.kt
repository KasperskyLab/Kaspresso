package com.kaspersky.components.uiautomator_dsl.intercepting.asserts

import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction

interface UiAssert {

    fun getType(): UiAssertType

    fun getDescription(): String

    fun check(objectInteraction: UiObjectInteraction)

}