package com.kaspersky.uitest_framework.kakaoext.dispatchers

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import com.kaspersky.uitest_framework.attempt.attempt

/**
 * Created by egor.kurnikov on 04.03.2019
 */

class FlakySafeInterceptedViewDispatcher(
        viewInteraction: ViewInteraction
): InterceptedViewDispatcher(viewInteraction) {

    override fun perform(viewAction: ViewAction): FlakySafeInterceptedViewDispatcher {
        attempt { super.perform(viewAction) }
        return this
    }

    override fun check(viewAssertion: ViewAssertion): FlakySafeInterceptedViewDispatcher {
        attempt { super.check(viewAssertion) }
        return this
    }
}