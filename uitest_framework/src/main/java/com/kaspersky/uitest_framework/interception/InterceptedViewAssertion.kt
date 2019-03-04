package com.kaspersky.uitest_framework.interception

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.view.View
import com.kaspersky.uitest_framework.Configuration

/**
 * Created by egor.kurnikov on 03.03.2019
 */

class InterceptedViewAssertion(
        private val viewAssertion: ViewAssertion
) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        Configuration.viewAssertionInterceptors.forEach { viewAssertionInterceptor ->
            viewAssertionInterceptor.intercept(viewAssertion, view, noViewFoundException)
        }

        viewAssertion.check(view, noViewFoundException)
    }
}