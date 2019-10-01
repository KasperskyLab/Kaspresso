package com.kaspersky.kaspresso.obtain_value.actions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class ObtainVisibilityViewAction : ObtainValueViewAction {
    override var value: String = "with visibility \""

    override fun getConstraints(): Matcher<View> = ViewMatchers.isAssignableFrom(View::class.java)

    override fun getDescription(): String = "getting visibility from a View"

    override fun perform(uiController: UiController?, view: View?) {
        value += when (view?.visibility) {
            View.VISIBLE -> "VISIBLE"
            View.INVISIBLE -> "INVISIBLE"
            View.GONE -> "GONE"
            else -> "[UNDEFINED]"
        }
    }
}