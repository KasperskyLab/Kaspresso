package com.agoda.kakao.delegates.impl

import android.view.View
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.DataInteractionDelegate
import com.agoda.kakao.delegates.ViewInteractionDelegate

import org.hamcrest.Matcher

class DataInteractionDelegateEmptyImpl internal constructor(
        private val dataInteraction: DataInteraction
): DataInteractionDelegate {

    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        return KakaoConfigurator.createViewInteractionDelegate(
                dataInteraction.check(viewAssertion)
        )
    }
}