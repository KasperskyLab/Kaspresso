package com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion

class UiActionBaseImpl<VIEW>(
    private val type: UiActionType,
    private val description: String?,
    private val action: VIEW.() -> Unit
) : UiAction<VIEW> {

    override fun getType(): UiActionType = type

    override fun getDescription(): String? = description

    override fun execute(innerView: VIEW) = action.invoke(innerView)
}