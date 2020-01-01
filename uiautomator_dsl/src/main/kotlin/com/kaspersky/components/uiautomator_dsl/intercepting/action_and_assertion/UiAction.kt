package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

/**
 * Responsible for executing an interaction on the element of UiAutomator
 */
interface UiAction<VIEW> {

    fun getType(): UiActionType

    fun getDescription(): String?

    fun execute(innerView: VIEW)

}