@file:Suppress("unused")

package com.agoda.kakao.common.builders

import android.support.test.espresso.Root
import android.support.test.espresso.matcher.RootMatchers
import com.agoda.kakao.common.KakaoDslMarker
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

/**
 * Class for building root matchers
 *
 * This class helps to build matches for root.
 * Please note that any function invoking will add specific matcher to the list
 * and after that all of them will be combined with help of AllOf.allOf()
 *
 * @see AllOf.allOf()
 */
@KakaoDslMarker
class RootBuilder {
    private val rootMatchers = arrayListOf<Matcher<Root>>()

    /**
     * Matches root that is dialog
     */
    fun isDialog() {
        rootMatchers.add(RootMatchers.isDialog())
    }

    /**
     * Matches root that is not dialog
     */
    fun isNotDialog() {
        rootMatchers.add(Matchers.not(RootMatchers.isDialog()))
    }

    /**
     * Matches root that is focusable
     */
    fun isFocusable() {
        rootMatchers.add(RootMatchers.isFocusable())
    }

    /**
     * Matches root that is not focusable
     */
    fun isNotFocusable() {
        rootMatchers.add(Matchers.not(RootMatchers.isFocusable()))
    }

    /**
     * Matches root that is platform popup
     */
    fun isPlatformPopup() {
        rootMatchers.add(RootMatchers.isPlatformPopup())
    }

    /**
     * Matches root that is not platform popup
     */
    fun isNotPlatformPopup() {
        rootMatchers.add(Matchers.not(RootMatchers.isPlatformPopup()))
    }

    /**
     * Matches root that is touchable
     */
    fun isTouchable() {
        rootMatchers.add(RootMatchers.isTouchable())
    }

    /**
     * Matches root that is not touchable
     */
    fun isNotTouchable() {
        rootMatchers.add(Matchers.not(RootMatchers.isTouchable()))
    }

    /**
     * Matches root that has decor view matching given matcher
     *
     * @param function ViewBuilder which will result in decor view matcher
     */
    fun withDecorView(function: ViewBuilder.() -> Unit) {
        rootMatchers.add(RootMatchers.withDecorView(ViewBuilder().apply(function).getViewMatcher()))
    }

    /**
     * Matches root with given custom matcher
     *
     * @param matcher Custom root matcher to be added
     */
    fun withMatcher(matcher: Matcher<Root>) {
        rootMatchers.add(matcher)
    }

    /**
     * Returns combined root matchers with AllOf.allOf()
     *
     * @return Matcher<Root>
     */
    fun getRootMatcher(): Matcher<Root> = AllOf.allOf(rootMatchers)
}