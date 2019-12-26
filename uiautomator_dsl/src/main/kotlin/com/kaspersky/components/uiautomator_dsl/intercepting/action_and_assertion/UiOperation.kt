package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

interface UiOperation<INTERACTION> {

    fun getType(): UiOperationType

    fun getDescription(): String?

    fun execute(interaction: INTERACTION)

}