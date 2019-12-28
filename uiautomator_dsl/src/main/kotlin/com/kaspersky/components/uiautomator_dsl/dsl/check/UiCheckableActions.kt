package com.kaspersky.components.uiautomator_dsl.dsl.check
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType
import com.kaspersky.components.uiautomator_dsl.dsl.check.UiCheckableActions.CheckableActionType.SET_CHECKED

interface UiCheckableActions : UiBaseActions {

    /**
     * Sets checked state of the view
     *
     * @param checked True if checked, false otherwise
     */
    fun setChecked(checked: Boolean) {
        actionsView.perform(SET_CHECKED, "checked=$checked") {
            if (isChecked != checked) click()
        }
    }

    enum class CheckableActionType : UiOperationType {
        SET_CHECKED
    }


}