package com.kaspersky.components.uiautomatordsl.intercepting.operation

class UiOperationBaseImpl<View>(
    override val type: UiOperationType,
    override val description: String?,
    private val action: View.() -> Unit
) : UiOperation<View> {

    override fun execute(innerView: View) = action.invoke(innerView)
}