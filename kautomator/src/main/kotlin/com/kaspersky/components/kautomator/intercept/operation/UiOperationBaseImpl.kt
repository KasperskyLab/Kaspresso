package com.kaspersky.components.kautomator.intercept.operation

class UiOperationBaseImpl<View>(
    override val type: UiOperationType,
    override val description: String?,
    private val action: View.() -> Unit
) : UiOperation<View> {

    override fun execute(innerView: View) = action.invoke(innerView)

    override fun toString(): String {
        return "UiOperationBaseImpl(type=$type, description=$description, action=$action)"
    }
}