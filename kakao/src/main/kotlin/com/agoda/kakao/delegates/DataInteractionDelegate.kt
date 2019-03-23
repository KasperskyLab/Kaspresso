package com.agoda.kakao.delegates

import android.view.View
import android.support.annotation.CheckResult
import android.support.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

/**
 * An interface duplicating the used in Kakao part of data interaction interface.
 * If you want to extend the base data interaction behavior, you should implement this interface, and specify it's
 * factory in [com.agoda.kakao.configurator.KakaoConfigurator]
 */
interface DataInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate

    fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate
}