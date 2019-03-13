package com.agoda.kakao.delegates.impl

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.view.View
import com.agoda.kakao.configuration.Configurator
import com.agoda.kakao.delegates.DataInteractionDelegate
import com.agoda.kakao.delegates.ViewInteractionDelegate

import org.hamcrest.Matcher

class DataInteractionDelegateEmptyImpl internal constructor(
        override val dataInteraction: DataInteraction
): DataInteractionDelegate {

    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        return Configurator.viewInteractionDelegateFactory.invoke(
                dataInteraction.check(viewAssertion)
        )
    }
}