package com.kaspersky.components.uiautomator_dsl.dsl.common.actions

import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiProxy
import com.kaspersky.components.uiautomator_dsl.dsl.common.actions.UiBaseActions.BaseUiOperationType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiOperationType

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

    enum class BaseUiOperationType :
        UiOperationType {
        CLICK,
        DOUBLE_CLICK,
        LONG_CLICK
    }
}