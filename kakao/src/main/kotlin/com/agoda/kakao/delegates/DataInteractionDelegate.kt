package com.agoda.kakao.delegates

import android.view.View
import android.support.annotation.CheckResult
import android.support.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

interface DataInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate
}