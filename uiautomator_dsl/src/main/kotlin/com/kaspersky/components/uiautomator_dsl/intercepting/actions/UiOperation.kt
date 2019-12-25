package com.kaspersky.components.uiautomator_dsl.intercepting.actions

interface UiOperation<INTERACTION> {

    fun getType(): UiOperationType

    fun getDescription(): String

    fun execute(interaction: INTERACTION)

}