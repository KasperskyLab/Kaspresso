@file:Suppress("unused")

package com.agoda.kakao.text

import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.common.matchers.AnyTextMatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Provides text based assertions for views
 */
interface TextViewAssertions : BaseAssertions {
    /**
     * Checks if the view have not any text
     */
    fun hasEmptyText() {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText("")))
    }

    /**
     * Checks if the view has any text
     */
    fun hasAnyText() {
        view.check(ViewAssertions.matches(
                AnyTextMatcher()))
    }

    /**
     * Checks if the view has given text
     *
     * @param text Text to be matched
     */
    fun hasText(text: String) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText(text)))
    }

    /**
     * Checks if the view has given text
     *
     * @param resId String resource to be matched
     */
    fun hasText(@StringRes resId: Int) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText(resId)))
    }

    /**
     * Checks if the view has text that matches given matcher
     */
    fun hasText(matcher: Matcher<String>) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText(matcher)))
    }

    /**
     * Checks if the view has given text color
     *
     * @param resId Color resource to be matched
     */
    fun hasTextColor(@ColorRes resId: Int) {
        view.check(ViewAssertions.matches(
                ViewMatchers.hasTextColor(resId)))
    }

    /**
     * Checks if the view does not have a given text
     *
     * @param text Text to be matched
     */
    fun hasNoText(text: String) {
        view.check(ViewAssertions.matches(CoreMatchers.not(
                ViewMatchers.withText(text))))
    }

    /**
     * Checks if the view does not have a given text
     *
     * @param resId String resource to be matched
     */
    fun hasNoText(@StringRes resId: Int) {
        view.check(ViewAssertions.matches(CoreMatchers.not(
                ViewMatchers.withText(resId))))
    }

    /**
     * Checks if the view has given content description
     *
     * @param text Content description to be matched
     */
    fun hasContentDescription(text: String) {
        view.check(ViewAssertions.matches(ViewMatchers.withContentDescription(text)))
    }

    /**
     * Checks if the view contains given text
     *
     * @param text Text to be searched
     */
    fun containsText(text: String) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText(Matchers.containsString(text))))
    }

    /**
     * Checks if the view text start with given substring
     *
     * @param text Text to be searched
     */
    fun startsWithText(text: String) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withText(Matchers.startsWith(text))))
    }
}