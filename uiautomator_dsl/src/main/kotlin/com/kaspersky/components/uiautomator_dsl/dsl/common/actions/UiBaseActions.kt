package com.kaspersky.components.uiautomator_dsl.dsl.common.actions

import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiObjectDelegate
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions.BaseUiActionType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

/**
 * Base interface for performing actions on UiAutomator view
 *
 * Provides a lot of basic action methods, such as click(), etc.
 *
 * @see com.kaspersky.components.uiautomator_dsl.dsl.edit.UiEditableActions
 */
interface UiBaseActions {
    val view: UiObjectDelegate

    /**
     * Performs click on view
     */
    fun click() {
        view.perform(CLICK) { click() }
    }

    /**
     * Performs double click on view
     */
    fun doubleClick() {
        view.perform(DOUBLE_CLICK) {
            click()
            click()
        }
    }

    /**
     * Performs long click on view
     */
    fun longClick() {
        view.perform(LONG_CLICK) { longClick() }
    }

    enum class BaseUiActionType : UiActionType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}