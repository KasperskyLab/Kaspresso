package com.kaspersky.uitest_framework.kakao.proxy

import android.support.test.espresso.FailureHandler
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.FailureHandlerInterceptor
import org.hamcrest.Matcher

class FailureHandlerProxy(
        private val failureHandler: FailureHandler,
        private val interceptors: List<FailureHandlerInterceptor>
): FailureHandler {

    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {

        interceptors.forEach { failureHandlerInterceptor ->
            failureHandlerInterceptor.intercept(failureHandler, error, viewMatcher)
        }

        failureHandler.handle(error, viewMatcher)
    }
}