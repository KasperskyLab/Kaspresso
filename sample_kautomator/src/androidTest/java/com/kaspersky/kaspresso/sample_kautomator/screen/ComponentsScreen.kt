package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.dsl.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.dsl.check.UiCheckBox
import com.kaspersky.components.kautomator.dsl.chip.UiChipGroup
import com.kaspersky.components.kautomator.dsl.dialog.UiAlertDialog
import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.dsl.text.UiButton

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    private const val MAIN_APP_PACKAGE_ID = "com.kaspersky.kaspresso.sample_kautomator"

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "checkBox") }
    val chipGroup = UiChipGroup { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "chipGroup") }
    val showDialogBtn = UiButton { withId(this@ComponentsScreen.MAIN_APP_PACKAGE_ID, "showDialogBtn") }
    val dialog = UiAlertDialog(this@ComponentsScreen.MAIN_APP_PACKAGE_ID)
}