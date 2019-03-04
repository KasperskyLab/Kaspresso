package com.kaspersky.uitest_framework.kakaoext

import android.support.test.espresso.Espresso
import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakao.ViewBuilder
import com.kaspersky.uitest_framework.kakaoext.dispatchers.FlakySafeInterceptedViewDispatcher
import com.kaspersky.uitest_framework.kakaoext.dispatchers.InterceptedViewDispatcher
import org.hamcrest.core.AllOf

/**
 * Created by egor.kurnikov on 04.03.2019
 */

class InterceptedViewBuilder : ViewBuilder() {

    override fun getViewDispatcher(): InterceptedViewDispatcher {
        check(viewMatchers.isNotEmpty()) { "No matchers inside InteractionBuilder" }
        return InterceptedViewDispatcher(Espresso.onView(AllOf.allOf(viewMatchers)))
    }
}

class FlakySafeInterceptedViewBuilder : ViewBuilder() {

    override fun getViewDispatcher(): FlakySafeInterceptedViewDispatcher {
        check(viewMatchers.isNotEmpty()) { "No matchers inside InteractionBuilder" }
        return FlakySafeInterceptedViewDispatcher(Espresso.onView(AllOf.allOf(viewMatchers)))
    }
}

val configuredViewBuilder: ViewBuilder
    get() {
        return if (Configuration.enableFlakySafety) {
            FlakySafeInterceptedViewBuilder()
        } else {
            InterceptedViewBuilder()
        }
    }