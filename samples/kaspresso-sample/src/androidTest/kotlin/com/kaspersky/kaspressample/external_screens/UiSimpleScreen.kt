package com.kaspersky.kaspressample.external_screens

import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.simple.SimpleActivity

object UiSimpleScreen : UiSampleScreen<UiSimpleScreen>() {

    override val layoutId: Int? = R.layout.activity_simple
    override val viewClass: Class<*>? = SimpleActivity::class.java

    val button1 = UiButton { withId(this@UiSimpleScreen.packageName, "button_1") }

    val button2 = UiButton { withId(this@UiSimpleScreen.packageName, "button_2") }

    val edit = UiEditText { withId(this@UiSimpleScreen.packageName, "edit") }
}
