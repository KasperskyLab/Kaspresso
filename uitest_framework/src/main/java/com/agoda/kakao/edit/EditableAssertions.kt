@file:Suppress("unused")

package com.agoda.kakao.edit

import android.support.annotation.StringRes
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.text.TextViewAssertions

/**
 * Provides editable based assertions for views
 */
interface EditableAssertions : TextViewAssertions {
    /**
     * Checks if the view has given hint
     *
     * @param hint Text to be matched
     */
    fun hasHint(hint: String) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withHint(hint)))
    }

    /**
     * Checks if the view has given hint
     *
     * @param resId String resource to be matched
     */
    fun hasHint(@StringRes resId: Int) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withHint(resId)))
    }
}