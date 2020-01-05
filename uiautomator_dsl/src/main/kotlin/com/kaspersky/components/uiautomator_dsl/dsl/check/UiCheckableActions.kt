package com.kaspersky.components.uiautomator_dsl.dsl.check
import com.kaspersky.components.uiautomator_dsl.dsl.check.UiCheckableActions.CheckableActionType.SET_CHECKED
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiActionType

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

    enum class CheckableActionType : UiActionType {
        SET_CHECKED
    }
}