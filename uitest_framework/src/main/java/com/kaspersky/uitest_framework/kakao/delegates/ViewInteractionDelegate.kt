package com.kaspersky.uitest_framework.kakao.delegates

import android.support.test.espresso.*
import android.view.View
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.kakao.proxy.ViewActionProxy
import com.kaspersky.uitest_framework.kakao.proxy.ViewAssertionProxy
import org.hamcrest.Matcher

open class ViewInteractionDelegate(
        private val viewInteraction: ViewInteraction
) {
    private var isCustomFailureHandlerSet = false

    open fun perform(viewAction: ViewAction): ViewInteractionDelegate {

        setFailureHandlerIfNecessary()

        val viewActionProxy = ViewActionProxy(
                viewAction,
                InterceptorsHolder.viewActionInterceptors
        )

        execute { viewInteraction.perform(viewActionProxy) }

        return this
    }

    open fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        setFailureHandlerIfNecessary()

        val viewAssertionProxy = ViewAssertionProxy(
                viewAssertion,
                InterceptorsHolder.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }

        return this
    }

    open fun check(function: (View, NoMatchingViewException) -> Unit): ViewInteractionDelegate {

        setFailureHandlerIfNecessary()

        val viewAssertionProxy = ViewAssertionProxy(
                ViewAssertion(function),
                InterceptorsHolder.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }

        return this
    }

    open fun withFailureHandler(
            function: (Throwable, Matcher<View>) -> Unit
    ): ViewInteractionDelegate {

        isCustomFailureHandlerSet = true

        viewInteraction.withFailureHandler(function)

        return this
    }

    open fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate {

        setFailureHandlerIfNecessary()

        viewInteraction.inRoot(rootMatcher)

        return this
    }

    private fun setFailureHandlerIfNecessary() {

        InterceptorsHolder.failureInterceptor?.let { failureInterceptor ->
            if (!isCustomFailureHandlerSet) {
                withFailureHandler(failureInterceptor::interceptAndThrow)
            }
        }
    }

    private fun execute(executable: () -> ViewInteraction): ViewInteraction {

        return InterceptorsHolder.executingInterceptor
                ?.interceptAndExecute { executable.invoke() }
                ?: executable.invoke()
    }
}