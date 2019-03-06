@file:Suppress("unused")

package com.kaspersky.uitest_framework.kakao.web

import android.support.test.espresso.web.assertion.WebViewAssertions
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import android.support.test.espresso.web.webdriver.DriverAtoms
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Interface that provides assertions for WebViews
 */
interface WebAssertions {
    val web: Web.WebInteraction<*>
    val ref: Atom<ElementReference>

    /**
     * Checks if element has given text
     *
     * @param text Text to be matched
     */
    fun hasText(text: String) {
        matches(DriverAtoms.getText(), Matchers.`is`(text))
    }

    /**
     * Checks if element contains given text
     *
     * @param text Text to be searched
     */
    fun containsText(text: String) {
        matches(DriverAtoms.getText(), CoreMatchers.containsString(text))
    }

    /**
     * Checks if element matches given matcher
     *
     * @param T Type of value to be matched
     * @param value Actual value to be matched
     * @param matcher Matcher that matches given value
     */
    fun <T> matches(value: Atom<T>, matcher: Matcher<T>) {
        web.withElement(ref)
                .check(WebViewAssertions.webMatches(value, matcher))
    }
}