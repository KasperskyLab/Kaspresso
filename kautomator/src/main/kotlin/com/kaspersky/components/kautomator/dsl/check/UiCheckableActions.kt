@file:Suppress("unused")
package com.kaspersky.components.kautomator.dsl.check

import com.kaspersky.components.kautomator.dsl.check.UiCheckableActions.CheckableActionType.SET_CHECKED
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.intercepting.operation.UiOperationType

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

    enum class CheckableActionType : UiOperationType {
        SET_CHECKED
    }
}