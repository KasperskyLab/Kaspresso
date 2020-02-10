@file:Suppress("unused")
package com.kaspersky.components.kautomator.component.check

import com.kaspersky.components.kautomator.component.check.UiCheckableActions.UiCheckableActionType.SET_CHECKED
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercept.operation.UiOperationType

/**
 * Provides actions for checkable views
 */
interface UiCheckableActions : UiBaseActions {

    /**
     * Sets checked state of the view
     *
     * @param checked True if checked, false otherwise
     */
    fun setChecked(checked: Boolean) {
        view.perform(SET_CHECKED, "checked=$checked") {
            if (isChecked != checked) click()
        }
    }

    enum class UiCheckableActionType : UiOperationType {
        SET_CHECKED
    }
}