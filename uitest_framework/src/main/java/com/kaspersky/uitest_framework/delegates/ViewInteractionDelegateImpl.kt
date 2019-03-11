package com.kaspersky.uitest_framework.delegates

import android.support.test.espresso.*
import android.view.View
import com.kaspersky.uitest_framework.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.proxy.ViewActionProxy
import com.kaspersky.uitest_framework.proxy.ViewAssertionProxy
import org.hamcrest.Matcher

open class ViewInteractionDelegateImpl(
        override val viewInteraction: ViewInteraction
): ViewInteractionDelegate {

    private var isCustomFailureHandlerSet = false

    override fun perform(viewAction: ViewAction): ViewInteractionDelegate {

        val viewActionProxy = ViewActionProxy(
                viewAction,
                InterceptorsHolder.viewActionInterceptors
        )

        execute { viewInteraction.perform(viewActionProxy) }

        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                viewAssertion,
                InterceptorsHolder.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }

        return this
    }

    override fun check(function: (View, NoMatchingViewException) -> Unit): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
                ViewAssertion(function),
                InterceptorsHolder.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }

        return this
    }

    override fun withFailureHandler(
            function: (Throwable, Matcher<View>) -> Unit
    ): ViewInteractionDelegate {

        isCustomFailureHandlerSet = true

        viewInteraction.withFailureHandler(function)

        return this
    }

    override fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate {

        setFailureHandlerIfNecessary()

        viewInteraction.inRoot(rootMatcher)

        return this
    }

    private fun execute(executable: () -> ViewInteraction): ViewInteraction {

        setFailureHandlerIfNecessary()

        return InterceptorsHolder.executingInterceptor
                ?.interceptAndExecute { executable.invoke() }
                ?: executable.invoke()
    }

    private fun setFailureHandlerIfNecessary() {

        InterceptorsHolder.failureInterceptor?.let { failureInterceptor ->
            if (!isCustomFailureHandlerSet) {
                withFailureHandler(failureInterceptor::interceptAndThrow)
            }
        }
    }
}