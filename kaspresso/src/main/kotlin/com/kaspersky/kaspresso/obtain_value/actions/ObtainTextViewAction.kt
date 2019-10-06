package com.kaspersky.kaspresso.obtain_value.actions

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class ObtainTextViewAction : ObtainValueViewAction {
    override var value: String = "with text \""

    override fun getConstraints(): Matcher<View> = ViewMatchers.isAssignableFrom(TextView::class.java)

    override fun getDescription(): String = "getting text from a TextView"

    override fun perform(uiController: UiController?, view: View?) {
        value += "${(view as TextView).text}\""
    }
}