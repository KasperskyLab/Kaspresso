package com.agoda.kakao.delegates

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.Root
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import org.hamcrest.Matcher

/**
 * An interface duplicating the used in Kakao part of view interaction interface.
 * If you want to extend the base view interaction behavior, you should implement this interface, and specify it's
 * factory in [com.agoda.kakao.configurator.KakaoConfigurator]
 */
interface ViewInteractionDelegate {

    fun perform(viewAction: ViewAction): ViewInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate

    fun check(function: (View, NoMatchingViewException?) -> Unit): ViewInteractionDelegate

    fun withFailureHandler(function: (Throwable, Matcher<View>) -> Unit): ViewInteractionDelegate

    fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate
}
