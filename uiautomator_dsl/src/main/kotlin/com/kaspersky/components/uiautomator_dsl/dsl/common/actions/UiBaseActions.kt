package com.kaspersky.components.uiautomator_dsl.dsl.common.actions

import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiProxy
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions.BaseActionType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.`object`.UiActionType

interface UiBaseActions {
    val actionsView: UiProxy

    fun click() {
        actionsView.perform(CLICK) { click() }
    }

    fun doubleClick() {
        actionsView.perform(DOUBLE_CLICK) {
            click()
            click()
        }
    }

    fun longClick() {
        actionsView.perform(LONG_CLICK) { longClick() }
    }

    enum class BaseActionType : UiActionType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}