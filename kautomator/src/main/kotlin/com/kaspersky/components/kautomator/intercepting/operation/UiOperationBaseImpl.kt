package com.kaspersky.components.kautomator.intercepting.operation

class UiOperationBaseImpl<View>(
    override val type: UiOperationType,
    override val description: String?,
    private val action: View.() -> Unit
) : UiOperation<View> {

    override fun execute(innerView: View) = action.invoke(innerView)
}