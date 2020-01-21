package com.kaspersky.components.kautomator.intercepting.interaction

interface UiInteraction<Assertion, Action> {

    fun check(assertion: Assertion)

    fun perform(action: Action)
}