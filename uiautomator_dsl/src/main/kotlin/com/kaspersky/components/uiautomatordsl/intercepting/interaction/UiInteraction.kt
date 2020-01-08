package com.kaspersky.components.uiautomatordsl.intercepting.interaction

interface UiInteraction<Assertion, Action> {

    fun check(assertion: Assertion)

    fun perform(action: Action)
}