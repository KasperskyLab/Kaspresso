package com.kaspersky.uitest_framework.kakao.dispatchers

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.view.View
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

/**
 * Created by egor.kurnikov on 04.03.2019
 */

open class DataDispatcher(protected val dataInteraction: DataInteraction) {

    @CheckResult
    @CheckReturnValue
    open fun onChildView(childMatcher: Matcher<View>): DataDispatcher {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    open fun check(assertion: ViewAssertion): ViewDispatcher {
        return ViewDispatcher(dataInteraction.check(assertion))
    }
}