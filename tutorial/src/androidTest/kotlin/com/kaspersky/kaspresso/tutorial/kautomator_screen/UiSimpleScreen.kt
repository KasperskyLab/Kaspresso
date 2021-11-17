package com.kaspersky.kaspresso.tutorial.kautomator_screen

import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen

object UiSimpleScreen : UiScreen<UiSimpleScreen>() {

    override val packageName: String = "com.kaspersky.kaspresso.tutorial"

    val button1 = UiButton { withId(this@UiSimpleScreen.packageName, "button_1") }
    val button2 = UiButton { withId(this@UiSimpleScreen.packageName, "button_2") }
    val edit = UiEditText { withId(this@UiSimpleScreen.packageName, "edit") }
}
