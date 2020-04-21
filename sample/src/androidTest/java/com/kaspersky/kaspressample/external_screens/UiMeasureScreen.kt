package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.check.UiCheckBox
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.component.text.UiTextView
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.measure.MeasureActivity

object UiMeasureScreen : UiSampleScreen<UiMeasureScreen>() {

    override val layoutId: Int? = R.layout.activity_measure
    override val viewClass: Class<*>? = MeasureActivity::class.java

    val button1 = UiButton { withId(this@UiMeasureScreen.packageName, "button_1") }

    val button2 = UiButton { withId(this@UiMeasureScreen.packageName, "button_2") }

    val textView = UiTextView { withId(this@UiMeasureScreen.packageName, "textview") }

    val edit = UiEditText { withId(this@UiMeasureScreen.packageName, "edit") }

    val checkBox = UiCheckBox { withId(this@UiMeasureScreen.packageName, "checkBox") }
}