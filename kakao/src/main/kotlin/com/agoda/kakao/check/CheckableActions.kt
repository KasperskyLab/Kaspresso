@file:Suppress("unused")

package com.agoda.kakao.check

import android.view.View
import android.widget.Checkable
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.actions.BaseActions
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

/**
 * Provides action for checking views
 */
interface CheckableActions : BaseActions {
    /**
     * Sets checked state of the view
     *
     * @param checked True if checked, false otherwise
     */
    fun setChecked(checked: Boolean) {
        view.perform(object : ViewAction {
            override fun getDescription() = "performing CheckableViewAction: $checked"

            override fun getConstraints() = Matchers.allOf(ViewMatchers.isAssignableFrom(View::class.java),
                    object : TypeSafeMatcher<View>() {
                        override fun describeTo(description: Description) {
                            description.appendText("is assignable from class: " + Checkable::class.java)
                        }

                        override fun matchesSafely(view: View) = Checkable::class.java.isAssignableFrom(view.javaClass)
                    })

            override fun perform(uiController: UiController, view: View) {
                if (view is Checkable) view.isChecked = checked
            }
        })
    }
}