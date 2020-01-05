package com.kaspersky.kaspresso.extensions

import androidx.annotation.ColorRes
import androidx.test.espresso.ViewAssertion
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.common.builders.RootBuilder
import com.agoda.kakao.common.builders.ViewBuilder

/**
 * Assertions which don't throw exceptions but return just Boolean.
 * All assertions are using interceptors that developer set in Kaspresso.Builder
 */

fun BaseAssertions.isDisplayedBool() = boolWrap { isDisplayed() }

fun BaseAssertions.isNotDisplayedBool() = boolWrap { isNotDisplayed() }

fun BaseAssertions.isCompletelyDisplayedBool() = boolWrap { isCompletelyDisplayed() }

fun BaseAssertions.isNotCompletelyDisplayedBool() = boolWrap { isNotCompletelyDisplayed() }

fun BaseAssertions.isVisibleBool() = boolWrap { isVisible() }

fun BaseAssertions.isInvisibleBool() = boolWrap { isInvisible() }

fun BaseAssertions.isGoneBool() = boolWrap { isGone() }

fun BaseAssertions.isSelectedBool() = boolWrap { isSelected() }

fun BaseAssertions.isNotSelectedBool() = boolWrap { isNotSelected() }

fun BaseAssertions.isFocusedBool() = boolWrap { isFocused() }

fun BaseAssertions.isNotFocusedBool() = boolWrap { isNotFocused() }

fun BaseAssertions.isFocusableBool() = boolWrap { isFocusable() }

fun BaseAssertions.isNotFocusableBool() = boolWrap { isNotFocusable() }

fun BaseAssertions.isClickableBool() = boolWrap { isClickable() }

fun BaseAssertions.isNotClickableBool() = boolWrap { isNotClickable() }

fun BaseAssertions.isEnabledBool() = boolWrap { isEnabled() }

fun BaseAssertions.isDisabledBool() = boolWrap { isDisabled() }

fun BaseAssertions.hasTagBool(tag: String) = boolWrap { hasTag(tag) }

fun BaseAssertions.hasAnyTagBool(vararg tags: String) = boolWrap { hasAnyTag(*tags) }

fun BaseAssertions.doesNotExistBool() = boolWrap { doesNotExist() }

fun BaseAssertions.hasDescendantBool(function: ViewBuilder.() -> Unit) = boolWrap { hasDescendant(function) }

fun BaseAssertions.hasNotDescendantBool(function: ViewBuilder.() -> Unit) = boolWrap { hasNotDescendant(function) }

fun BaseAssertions.hasSiblingBool(function: ViewBuilder.() -> Unit) = boolWrap { hasSibling(function) }

fun BaseAssertions.hasNotSiblingBool(function: ViewBuilder.() -> Unit) = boolWrap { hasNotSibling(function) }

fun BaseAssertions.matchesBool(function: ViewBuilder.() -> Unit) = boolWrap { matches(function) }

fun BaseAssertions.notMatchesBool(function: ViewBuilder.() -> Unit) = boolWrap { notMatches(function) }

fun BaseAssertions.assertBool(function: () -> ViewAssertion) = boolWrap { assert(function) }

fun BaseAssertions.inRootBool(function: RootBuilder.() -> Unit) = boolWrap { inRoot(function) }

fun BaseAssertions.hasBackgroundColorBool(@ColorRes resId: Int) = boolWrap { hasBackgroundColor(resId) }

fun BaseAssertions.hasBackgroundColorBool(colorCode: String) = boolWrap { hasBackgroundColor(colorCode) }

fun BaseAssertions.isCompletelyAboveBool(function: ViewBuilder.() -> Unit) = boolWrap { isCompletelyAbove(function) }

fun BaseAssertions.isCompletelyBelowBool(function: ViewBuilder.() -> Unit) = boolWrap { isCompletelyBelow(function) }

fun BaseAssertions.isCompletelyLeftOfBool(function: ViewBuilder.() -> Unit) = boolWrap { isCompletelyLeftOf(function) }

fun BaseAssertions.isCompletelyRightOfBool(function: ViewBuilder.() -> Unit) = boolWrap { isCompletelyRightOf(function) }