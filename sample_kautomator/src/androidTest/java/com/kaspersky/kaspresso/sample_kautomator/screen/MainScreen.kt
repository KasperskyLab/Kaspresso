package com.kaspersky.kaspresso.sample_kautomator.screen

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspresso.sample_kautomator.MainActivity
import com.kaspersky.kaspresso.sample_kautomator.R

object MainScreen : UiScreen<MainScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.sample_kautomator"
    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java

    val simpleEditText = UiEditText { withId(this@MainScreen.packageName, "editText") }
    val simpleButton = UiButton { withId(this@MainScreen.packageName, "button") }
    val checkBox = UiCheckBox { withId(this@MainScreen.packageName, "checkBox") }
}
