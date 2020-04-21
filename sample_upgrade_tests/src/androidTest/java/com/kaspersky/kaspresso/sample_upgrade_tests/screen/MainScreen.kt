package com.kaspersky.kaspresso.sample_upgrade_tests.screen

import com.kaspersky.components.kautomator.component.text.UiButton

object MainScreen : UpgradeUiScreen<MainScreen>() {

    val upgradeButton = UiButton { withId(this@MainScreen.packageName, "activity_main_button_upgrade_scenario") }
}
