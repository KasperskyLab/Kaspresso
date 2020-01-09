package com.kaspersky.components.kautomator.intercepting.operation

/**
 * Responsible for executing an interaction on the element of UiAutomator
 */
interface UiOperation<View> {

    val type: UiOperationType

    val description: String?

    fun execute(innerView: View)
}