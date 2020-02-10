package com.kaspersky.components.kautomator.intercept.interaction

interface UiInteraction<Assertion, Action> {

    fun check(assertion: Assertion)

    fun perform(action: Action)
}