package com.kaspersky.uitest_framework.kakao.proxy

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.ExecutingInterceptor
import com.kaspersky.uitest_framework.kakao.interceptors.ViewAssertionInterceptor

class ViewAssertionProxy(
        private val viewAssertion: ViewAssertion,
        private val interceptors: List<ViewAssertionInterceptor>
) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        interceptors.forEach { it.intercept(viewAssertion, view, noViewFoundException) }

        viewAssertion.check(view, noViewFoundException)
    }
}