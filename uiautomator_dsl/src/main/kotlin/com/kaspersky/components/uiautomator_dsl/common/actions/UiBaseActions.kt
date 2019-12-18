package com.kaspersky.components.uiautomator_dsl.common.actions

import com.kaspersky.components.uiautomator_dsl.proxy.UiObject2Proxy

interface UiBaseActions {
    val actionsView: UiObject2Proxy

    fun click() {
        actionsView.perform("click") { click() }
    }

    fun doubleClick() {
        actionsView.perform("doubleClick") {
            click()
            click()
        }
    }

    fun longClick() {
        actionsView.perform("longClick") { longClick() }
    }
}