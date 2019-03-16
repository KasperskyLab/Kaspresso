@file:Suppress("unused")

package com.agoda.kakao.common.assertions

import android.support.annotation.ColorRes
import android.support.test.espresso.Root
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.builders.RootBuilder
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.matchers.BackgroundColorMatcher
import com.agoda.kakao.delegates.ViewInteractionDelegate
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Base interface for asserting views
 *
 * Provides basic assertions that can be performed on any view
 *
 * @see TextViewAssertions
 * @see EditableAssertions
 * @see CheckableAssertions
 * @see ViewPagerAssertions
 * @see ImageViewAssertions
 */
interface BaseAssertions {
    val view: ViewInteractionDelegate
    var root: Matcher<Root>

    /**
     * Checks if the view is displayed
     */
    fun isDisplayed() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isDisplayed()))
    }

    /**
     * Checks if the view is not displayed
     */
    fun isNotDisplayed() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isDisplayed())))
    }

    /**
     * Checks if the view is completely displayed
     */
    fun isCompletelyDisplayed() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isCompletelyDisplayed()))
    }

    /**
     * Checks if the view is not completely displayed
     */
    fun isNotCompletelyDisplayed() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isCompletelyDisplayed())))
    }

    /**
     * Checks if the view has VISIBLE visibility
     */
    fun isVisible() {
        view.check(ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE)))
    }

    /**
     * Checks if the view has INVISIBLE visibility
     */
    fun isInvisible() {
        view.check(ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }

    /**
     * Checks if the view has GONE visibility
     */
    fun isGone() {
        view.check(ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    /**
     * Checks if the view is selected
     */
    fun isSelected() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isSelected()))
    }

    /**
     * Checks if the view is not selected
     */
    fun isNotSelected() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isSelected())))
    }

    /**
     * Checks if the view is focused
     */
    fun isFocused() {
        view.check(ViewAssertions.matches(
                ViewMatchers.hasFocus()))
    }

    /**
     * Checks if the view is not focused
     */
    fun isNotFocused() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.hasFocus())))
    }

    /**
     * Checks if the view is focusable
     */
    fun isFocusable() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isFocusable()))
    }

    /**
     * Checks if the view is not focusable
     */
    fun isNotFocusable() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isFocusable())))
    }

    /**
     * Checks if the view is clickable
     */
    fun isClickable() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isClickable()))
    }

    /**
     * Checks if the view is not clickable
     */
    fun isNotClickable() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isClickable())))
    }

    /**
     * Checks if the view is enabled
     */
    fun isEnabled() {
        view.check(ViewAssertions.matches(
                ViewMatchers.isEnabled()))
    }

    /**
     * Checks if the view is disabled
     */
    fun isDisabled() {
        view.check(ViewAssertions.matches(
                Matchers.not(ViewMatchers.isEnabled())))
    }

    /**
     * Checks if the view has given tag
     *
     * @param tag Tag that view must have
     */
    fun hasTag(tag: String) {
        view.check(ViewAssertions.matches(
                ViewMatchers.withTagValue(Matchers.`is`(tag))))
    }

    /**
     * Checks if the view has at least one of the given tags
     *
     * @param tags Tags with at least one of them should be present in view
     */
    fun hasAnyTag(vararg tags: String) {
        val matchers = ArrayList<Matcher<Any>>(tags.size)

        tags.forEach {
            matchers.add(Matchers.`is`(it))
        }

        view.check(ViewAssertions.matches(
                ViewMatchers.withTagValue(Matchers.anyOf(matchers))))
    }

    /**
     * Checks if the matched view does not exist
     */
    fun doesNotExist() {
        view.check(ViewAssertions.doesNotExist())
    }

    /**
     * Checks if the view has given descendant
     *
     * @param function ViewBuilder that will result in descendant matcher
     *
     * @see ViewBuilder
     */
    fun hasDescendant(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(ViewMatchers
                .hasDescendant(ViewBuilder().apply(function).getViewMatcher())))
    }

    /**
     * Checks if the view has not given descendant
     *
     * @param function ViewBuilder that will result in descendant matcher
     *
     * @see ViewBuilder
     */
    fun hasNotDescendant(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(Matchers.not(ViewMatchers
                .hasDescendant(ViewBuilder().apply(function).getViewMatcher()))))
    }

    /**
     * Checks if the view has given sibling
     *
     * @param function ViewBuilder that will result in sibling matcher
     *
     * @see ViewBuilder
     */
    fun hasSibling(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(ViewMatchers
                .hasSibling(ViewBuilder().apply(function).getViewMatcher())))
    }

    /**
     * Checks if the view has not given sibling
     *
     * @param function ViewBuilder that will result in sibling matcher
     *
     * @see ViewBuilder
     */
    fun hasNotSibling(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(Matchers.not(ViewMatchers
                .hasSibling(ViewBuilder().apply(function).getViewMatcher()))))
    }

    /**
     * Check if the view matches given matcher
     *
     * @param function ViewBuilder that will result in matcher
     *
     * @see ViewBuilder
     */
    fun matches(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Check if the view does not match given matcher
     *
     * @param function ViewBuilder that will result in matcher
     *
     * @see ViewBuilder
     */
    fun notMatches(function: ViewBuilder.() -> Unit) {
        view.check(ViewAssertions.matches(Matchers.not(ViewBuilder().apply(function).getViewMatcher())))
    }

    /**
     * Check the view with the given custom assertion
     *
     * @param function Lambda that must return ViewAssertion
     */
    fun assert(function: () -> ViewAssertion) {
        view.check(function.invoke())
    }

    /**
     * Check if the view is in given root
     *
     * @param function RootBuilder that will result in root matcher
     *
     * @see RootBuilder
     */
    fun inRoot(function: RootBuilder.() -> Unit) {
        root = RootBuilder().apply(function).getRootMatcher()
        view.inRoot(root)
    }

    /**
     *  Checks if the view has given background color
     *
     * @param resId Color resource to be matched
     *
     */
    fun hasBackgroundColor(@ColorRes resId: Int) {
        view.check(ViewAssertions.matches(BackgroundColorMatcher(resId = resId)))
    }

    /**
     *  Checks if the view has given background color
     *
     * @param colorCode Color string code to be matched
     *
     */
    fun hasBackgroundColor(colorCode: String) {
        view.check(ViewAssertions.matches(BackgroundColorMatcher(colorCode = colorCode)))
    }
}

