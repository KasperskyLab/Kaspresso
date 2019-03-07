package com.kaspersky.uitest_framework.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.kakao.proxy.MatcherProxy
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class DataInteractionDelegate(
        private val dataInteraction: DataInteraction
) {
    @CheckResult
    @CheckReturnValue
    open fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {

        val matcherProxy = MatcherProxy<View>(
                childMatcher,
                InterceptorsHolder.matcherInterceptors
        )

        dataInteraction.onChildView(matcherProxy)

        return this
    }

    open fun check(assertion: ViewAssertion): ViewInteractionDelegate {
        return ViewInteractionDelegate(dataInteraction.check(assertion))
    }
}