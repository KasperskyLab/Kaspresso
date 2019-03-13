package com.kaspersky.uitest_framework.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.view.View
import com.kaspersky.uitest_framework.configuration.InterceptorConfigurator
import com.agoda.kakao.delegates.DataInteractionDelegate
import com.agoda.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.proxy.ViewAssertionProxy
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class DataInteractionDelegateImpl(
        override val dataInteraction: DataInteraction
): DataInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                viewAssertion,
                InterceptorConfigurator.viewAssertionInterceptors
        )

        return ViewInteractionDelegateImpl(
                execute { dataInteraction.check(viewAssertionProxy) }
        )
    }

    private fun execute(executable: () -> ViewInteraction): ViewInteraction {

        return InterceptorConfigurator.executingInterceptor
                ?.interceptAndExecute { executable.invoke() }
                ?: executable.invoke()
    }
}