package com.agoda.kakao.delegates

import android.support.test.espresso.*
import android.view.View
import org.hamcrest.Matcher

interface ViewInteractionDelegate {

    fun perform(viewAction: ViewAction): ViewInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate

    fun check(function: (View, NoMatchingViewException) -> Unit): ViewInteractionDelegate

    fun withFailureHandler(function: (Throwable, Matcher<View>) -> Unit): ViewInteractionDelegate

    fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate
}