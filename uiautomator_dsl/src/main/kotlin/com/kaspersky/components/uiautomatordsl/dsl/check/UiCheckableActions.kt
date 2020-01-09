package com.kaspersky.components.uiautomatordsl.dsl.check
import com.kaspersky.components.uiautomatordsl.dsl.check.UiCheckableActions.CheckableActionType.SET_CHECKED
import com.kaspersky.components.uiautomatordsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiOperationType

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