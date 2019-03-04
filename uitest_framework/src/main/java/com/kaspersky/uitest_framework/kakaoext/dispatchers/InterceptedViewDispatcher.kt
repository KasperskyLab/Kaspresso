package com.kaspersky.uitest_framework.kakaoext.dispatchers

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import com.kaspersky.uitest_framework.interception.InterceptedViewAction
import com.kaspersky.uitest_framework.interception.InterceptedViewAssertion
import com.kaspersky.uitest_framework.kakao.dispatchers.ViewDispatcher

/**
 * Created by egor.kurnikov on 03.03.2019
 */

open class InterceptedViewDispatcher(
        viewInteraction: ViewInteraction
): ViewDispatcher(viewInteraction) {

    override fun perform(viewAction: ViewAction): InterceptedViewDispatcher {
        viewInteraction.perform(InterceptedViewAction(viewAction))
        return this
    }

    override fun check(viewAssertion: ViewAssertion): InterceptedViewDispatcher {
        viewInteraction.check(InterceptedViewAssertion(viewAssertion))
        return this
    }
}