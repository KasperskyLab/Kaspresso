@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.common.matchers

import android.view.View
import android.support.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * Matches first view
 */
class FirstViewMatcher : BoundedMatcher<View, View>(View::class.java) {
    private var matched: Boolean = false

    override fun describeTo(desc: Description) {
        desc.appendText("first view")
    }

    override fun matchesSafely(view: View?): Boolean {
        return if (matched) {
            false
        } else {
            matched = true
            true
        }
    }
}