package com.kaspersky.kaspresso.foreign_process_tests.screen

import com.kaspersky.components.uiautomator_dsl.common.views.UiView
import com.kaspersky.kaspresso.foreign_process_tests.common.UiScreen

object MainScreen : UiScreen<MainScreen>() {

    private const val UPGRADE_BUTTON_RES = "activity_main_button_upgrade_scenario"

    val upgradeButton get() = UiView { withId(MAIN_APP_PACKAGE_ID, UPGRADE_BUTTON_RES) }
}