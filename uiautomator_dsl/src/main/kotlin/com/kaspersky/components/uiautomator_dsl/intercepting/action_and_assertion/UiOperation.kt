package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

/**
 * Responsible for executing an interaction on the element of UiAutomator
 */
interface UiOperation<INTERACTION> {

    fun getType(): UiOperationType

    fun getDescription(): String?

    fun execute(interaction: INTERACTION)

}