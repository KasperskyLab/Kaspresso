package com.kaspersky.components.uiautomator_dsl.dsl.system

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptable
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.uiautomator_dsl.dsl.system.System.SystemOperationType.*
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperation
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiOperationType

object System : UiInterceptable<UiDeviceInteraction, UiOperation<UiDeviceInteraction>, UiOperation<UiDeviceInteraction>> {

    override val view: UiDeviceDelegate = UiDeviceDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    fun openNotification() {
        view.perform(OPEN_NOTIFICATION) { openNotification() }
    }

    fun openQuickSettings() {
        view.perform(OPEN_QUICK_SETTINGS) { openQuickSettings() }
    }

    fun pressDelete() {
        view.perform(PRESS_DELETE) { pressDelete() }
    }

    fun pressEnter() {
        view.perform(PRESS_ENTER) { pressEnter() }
    }

    fun pressHome() {
        view.perform(PRESS_HOME) { pressHome() }
    }

    fun pressMenu() {
        view.perform(PRESS_MENU) { pressMenu() }
    }

    fun pressRecentApps() {
        view.perform(PRESS_RECENT_APPS) { pressRecentApps() }
    }

    fun pressSearch() {
        view.perform(PRESS_SEARCH) { this.pressSearch() }
    }

    enum class SystemOperationType : UiOperationType {
        OPEN_NOTIFICATION,
        OPEN_QUICK_SETTINGS,
        PRESS_DELETE,
        PRESS_ENTER,
        PRESS_HOME,
        PRESS_MENU,
        PRESS_RECENT_APPS,
        PRESS_SEARCH
    }

}