package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton

object UiSimpleScreen : UiSampleScreen<UiSimpleScreen>() {

    val button1 = UiButton { withId(this@UiSimpleScreen.packageName, "button_1") }

    val button2 = UiButton { withId(this@UiSimpleScreen.packageName, "button_2") }

    val edit = UiEditText { withId(this@UiSimpleScreen.packageName, "edit") }
}