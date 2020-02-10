package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.dsl.check.UiCheckBox
import com.kaspersky.components.kautomator.dsl.edit.UiEditText
import com.kaspersky.components.kautomator.dsl.text.UiButton
import com.kaspersky.components.kautomator.dsl.text.UiTextView

object UiMeasureScreen : UiSampleScreen<UiMeasureScreen>() {

    val button1 = UiButton { withId(this@UiMeasureScreen.packageName, "button_1") }

    val button2 = UiButton { withId(this@UiMeasureScreen.packageName, "button_2") }

    val textView = UiTextView { withId(this@UiMeasureScreen.packageName, "textview") }

    val edit = UiEditText { withId(this@UiMeasureScreen.packageName, "edit") }

    val checkBox = UiCheckBox { withId(this@UiMeasureScreen.packageName, "checkBox") }
}