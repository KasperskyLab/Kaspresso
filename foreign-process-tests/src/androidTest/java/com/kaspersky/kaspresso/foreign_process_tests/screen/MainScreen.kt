package com.kaspersky.kaspresso.foreign_process_tests.screen

import com.kaspersky.components.uiautomator_dsl.text.UiButton
import com.kaspersky.kaspresso.foreign_process_tests.common.UpgradeUiScreen

object MainScreen : UpgradeUiScreen<MainScreen>() {

    private const val UPGRADE_BUTTON_RES = "activity_main_button_upgrade_scenario"

    val upgradeButton = UiButton { withId(MAIN_APP_PACKAGE_ID, UPGRADE_BUTTON_RES) }
}
