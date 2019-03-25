package com.kaspersky.uitest_framework.interceptors

import android.view.View
import org.hamcrest.Matcher

interface FailureInterceptor {

    fun interceptAndThrow(error: Throwable, viewMatcher: Matcher<View>)
}