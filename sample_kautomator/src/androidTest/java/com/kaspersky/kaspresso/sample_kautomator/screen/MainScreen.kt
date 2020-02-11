package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object MainScreen : UiScreen<MainScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"

    val simpleEditText = UiEditText { withId(this@MainScreen.packageName, "editText") }
    val simpleButton = UiButton { withId(this@MainScreen.packageName, "button") }
    val checkBox = UiCheckBox { withId(this@MainScreen.packageName, "checkBox") }
}
