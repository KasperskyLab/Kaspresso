package com.kaspersky.uitest_framework.kakao.delegates.impl

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.factory.DelegatesFactory
import com.kaspersky.uitest_framework.kakao.delegates.DataInteractionDelegate
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate

import org.hamcrest.Matcher

class DataInteractionDelegateImpl internal constructor(
        override val dataInteraction: DataInteraction
): DataInteractionDelegate {

    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        return DelegatesFactory.viewInteractionDelegateFactory.invoke(
                dataInteraction.check(viewAssertion)
        )
    }
}