package com.kaspersky.uitest_framework.kakao.interceptors

import android.support.test.espresso.FailureHandler
import android.view.View
import org.hamcrest.Matcher

interface FailureHandlerInterceptor {

    fun intercept(
            failureHandler: FailureHandler,
            error: Throwable?,
            viewMatcher: Matcher<View>?
    )
}