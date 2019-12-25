package com.kaspersky.components.uiautomator_dsl.intercepting.operations

interface UiOperation<INTERACTION> {

    fun getType(): UiOperationType

    fun getDescription(): String

    fun execute(interaction: INTERACTION)

}