package com.kaspersky.components.uiautomator_dsl.dsl.common.actions

import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions.BaseUiOperationType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType

/**
 * Base interface for performing actions on UiAutomator view
 *
 * Provides a lot of basic action methods, such as click(), etc.
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditableActions
 */
interface UiBaseActions {
    val actionsView: UiObjectDelegate

    /**
     * Performs click on view
     */
    fun click() {
        actionsView.perform(CLICK) { click() }
    }

    /**
     * Performs double click on view
     */
    fun doubleClick() {
        actionsView.perform(DOUBLE_CLICK) {
            click()
            click()
        }
    }

    /**
     * Performs long click on view
     */
    fun longClick() {
        actionsView.perform(LONG_CLICK) { longClick() }
    }

    enum class BaseUiOperationType :
        UiOperationType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}