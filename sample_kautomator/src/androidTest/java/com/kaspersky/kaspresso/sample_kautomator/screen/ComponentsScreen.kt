package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.dsl.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.dsl.check.UiCheckBox
import com.kaspersky.components.kautomator.dsl.chip.UiChipGroup
import com.kaspersky.components.kautomator.dsl.dialog.UiAlertDialog
import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.dsl.text.UiButton
import com.kaspersky.kaspresso.sample_kautomator.ComponentsActivity
import com.kaspersky.kaspresso.sample_kautomator.R

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"
    override val layoutId: Int? = R.layout.activity_ui_components
    override val viewClass: Class<*>? = ComponentsActivity::class.java

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.packageName, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.packageName, "checkBox") }
    val chipGroup = UiChipGroup { withId(this@ComponentsScreen.packageName, "chipGroup") }
    val showDialogBtn = UiButton { withId(this@ComponentsScreen.packageName, "showDialogBtn") }
    val dialog = UiAlertDialog(packageName)
}