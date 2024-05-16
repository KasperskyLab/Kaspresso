package com.kaspersky.kaspresso.kautomatorsample.screen

import com.kaspersky.components.kautomator.component.bottomnav.UiBottomNavigationView
import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.chip.UiChipGroup
import com.kaspersky.components.kautomator.component.dialog.UiAlertDialog
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.kautomatorsample.R

object ComponentsScreen : UiScreen<ComponentsScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.kautomatorsample"

    val bottomNav = UiBottomNavigationView { withId(this@ComponentsScreen.packageName, "bottomNav") }
    val checkbox = UiCheckBox { withId(this@ComponentsScreen.packageName, "checkBox") }
    val chipGroup = UiChipGroup { withId(this@ComponentsScreen.packageName, "chipGroup") }
    val showDialogBtn = UiButton { withId(this@ComponentsScreen.packageName, "showDialogBtn") }
    val dialog = UiAlertDialog(packageName)

    val editTextByHintResId = UiEditText { withHint(R.string.hint) }
    val editTextByHintText = UiEditText { withHint("Some hint") }
    val editTextByHintPattern = UiEditText { withHint("Some.*".toPattern()) }
}
