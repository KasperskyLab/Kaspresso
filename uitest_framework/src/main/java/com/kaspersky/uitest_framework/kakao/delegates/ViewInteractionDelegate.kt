package com.kaspersky.uitest_framework.kakao.delegates

import android.support.test.espresso.*
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.kakao.proxy.FailureHandlerProxy
import com.kaspersky.uitest_framework.kakao.proxy.MatcherProxy
import com.kaspersky.uitest_framework.kakao.proxy.ViewActionProxy
import com.kaspersky.uitest_framework.kakao.proxy.ViewAssertionProxy
import org.hamcrest.Matcher

open class ViewInteractionDelegate(
        private val viewInteraction: ViewInteraction
) {
    open fun perform(viewAction: ViewAction): ViewInteractionDelegate {

        val viewActionProxy = ViewActionProxy(
                viewAction,
                InterceptorsHolder.viewActionInterceptors,
                InterceptorsHolder.executingInterceptor
        )

        viewInteraction.perform(viewActionProxy)

        return this
    }

    open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                viewAssertion,
                InterceptorsHolder.viewAssertionInterceptors,
                InterceptorsHolder.executingInterceptor
        )

        viewInteraction.check(viewAssertionProxy)

        return this
    }

    open fun check(function: (View, NoMatchingViewException) -> Unit): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                ViewAssertion(function),
                InterceptorsHolder.viewAssertionInterceptors,
                InterceptorsHolder.executingInterceptor
        )

        viewInteraction.check(viewAssertionProxy)

        return this
    }

    open fun withFailureHandler(
            function: (Throwable, Matcher<View>) -> Unit
    ): ViewInteractionDelegate {

        val failureHandlerProxy = FailureHandlerProxy(
                FailureHandler(function),
                InterceptorsHolder.failureHandlerInterceptors
        )

        viewInteraction.withFailureHandler(failureHandlerProxy)

        return this
    }

    open fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate {

        val matherProxy = MatcherProxy<Root>(
                rootMatcher,
                InterceptorsHolder.matcherInterceptors
        )

        viewInteraction.inRoot(matherProxy)

        return this
    }
}