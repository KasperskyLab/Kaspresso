package com.agoda.kakao.delegates

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.Root
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import org.hamcrest.Matcher

interface ViewInteractionDelegate {

    fun perform(viewAction: ViewAction): ViewInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate

    fun check(function: (View, NoMatchingViewException) -> Unit): ViewInteractionDelegate

    fun withFailureHandler(function: (Throwable, Matcher<View>) -> Unit): ViewInteractionDelegate

    fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate
}