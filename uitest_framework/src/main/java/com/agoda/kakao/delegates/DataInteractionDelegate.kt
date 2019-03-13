package com.agoda.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.view.View
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

interface DataInteractionDelegate {

    val dataInteraction: DataInteraction

    @CheckResult
    @CheckReturnValue
    fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate
}