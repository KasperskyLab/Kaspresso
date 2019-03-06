package com.kaspersky.uitest_framework.kakao.interceptors

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.view.View

/**
 * Created by egor.kurnikov on 03.03.2019
 */

interface ViewAssertionInterceptor {

    fun intercept(
            viewAssertion: ViewAssertion,
            view: View?,
            exception: NoMatchingViewException?
    )
}