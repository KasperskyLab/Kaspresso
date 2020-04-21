@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.common.actions

import com.kaspersky.components.kautomator.intercept.delegate.UiObjectInteractionDelegate
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Base interface for performing actions on UiAutomator view
 *
 * Provides a lot of basic action methods, such as click(), etc.
 *
 * @see com.kaspersky.components.kautomator.component.edit.UiEditableActions
 */
interface UiBaseActions {
    val view: UiObjectInteractionDelegate

    /**
     * Performs click on view
     */
    fun click() {
        view.perform(UiBaseActionType.CLICK) { click() }
    }

    /**
     * Performs double click on view
     */
    fun doubleClick() {
        view.perform(UiBaseActionType.DOUBLE_CLICK) {
            click()
            click()
        }
    }

    /**
     * Performs long click on view
     */
    fun longClick() {
        view.perform(UiBaseActionType.LONG_CLICK) { longClick() }
    }

    enum class UiBaseActionType : UiOperationType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}