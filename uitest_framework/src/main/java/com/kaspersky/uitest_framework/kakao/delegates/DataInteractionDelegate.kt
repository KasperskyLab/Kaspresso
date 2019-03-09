package com.kaspersky.uitest_framework.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.kakao.proxy.ViewAssertionProxy
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class DataInteractionDelegate(
        private val dataInteraction: DataInteraction
) {
    @CheckResult
    @CheckReturnValue
    open fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                viewAssertion,
                InterceptorsHolder.viewAssertionInterceptors
        )

        return ViewInteractionDelegate(
                execute { dataInteraction.check(viewAssertionProxy) }
        )
    }

    private fun execute(executable: () -> ViewInteraction): ViewInteraction {

        return InterceptorsHolder.executingInterceptor
                ?.interceptAndExecute { executable.invoke() }
                ?: executable.invoke()
    }
}