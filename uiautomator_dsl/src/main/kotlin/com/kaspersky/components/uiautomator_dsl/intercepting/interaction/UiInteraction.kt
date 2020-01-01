package com.kaspersky.components.uiautomator_dsl.intercepting.interaction

interface UiInteraction<ASSERT, ACTION> {

    fun check(assertion: ASSERT)

    fun perform(action: ACTION)
}