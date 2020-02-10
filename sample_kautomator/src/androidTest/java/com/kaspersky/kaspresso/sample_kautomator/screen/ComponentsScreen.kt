package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.component.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.chip.UiChipGroup
import com.kaspersky.components.kautomator.component.dialog.UiAlertDialog
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.packageName, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.packageName, "checkBox") }
    val chipGroup = UiChipGroup { withId(this@ComponentsScreen.packageName, "chipGroup") }
    val showDialogBtn = UiButton { withId(this@ComponentsScreen.packageName, "showDialogBtn") }
    val dialog = UiAlertDialog(packageName)
}