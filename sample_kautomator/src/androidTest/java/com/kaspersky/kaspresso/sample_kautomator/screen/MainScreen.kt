package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.dsl.check.UiCheckBox
import com.kaspersky.components.kautomator.dsl.edit.UiEditText
import com.kaspersky.components.kautomator.dsl.screen.UiScreen
import com.kaspersky.components.kautomator.dsl.text.UiButton

object MainScreen : UiScreen<MainScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"

    val simpleEditText = UiEditText { withId(this@MainScreen.packageName, "editText") }
    val simpleButton = UiButton { withId(this@MainScreen.packageName, "button") }
    val checkBox = UiCheckBox { withId(this@MainScreen.packageName, "checkBox") }
}
