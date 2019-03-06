@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.check

import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.uitest_framework.kakao.common.assertions.BaseAssertions

/**
 * Provides checkable based assertions for views
 */
interface CheckableAssertions : BaseAssertions {
    /**
     * Checks if the view is checked
     */
    fun isChecked() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isChecked()))
    }

    /**
     * Checks if the view is not checked
     */
    fun isNotChecked() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isNotChecked()))
    }
}