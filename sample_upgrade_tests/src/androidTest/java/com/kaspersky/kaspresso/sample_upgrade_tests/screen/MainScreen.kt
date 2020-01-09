package com.kaspersky.kaspresso.sample_upgrade_tests.screen

import com.kaspersky.components.kautomator.dsl.text.UiButton
import com.kaspersky.kaspresso.sample_upgrade_tests.common.UpgradeUiScreen

object MainScreen : UpgradeUiScreen<MainScreen>() {

    val upgradeButton = UiButton { withId(MAIN_APP_PACKAGE_ID, "activity_main_button_upgrade_scenario") }
}
