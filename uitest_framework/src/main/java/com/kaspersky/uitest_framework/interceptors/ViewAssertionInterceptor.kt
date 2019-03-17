package com.kaspersky.uitest_framework.interceptors

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion

interface ViewAssertionInterceptor {

    fun intercept(
        viewAssertion: ViewAssertion,
        view: View?,
        exception: NoMatchingViewException?
    )
}