package com.kaspersky.kaspresso.delegates

import android.view.View
import android.support.test.espresso.*
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy
import com.kaspersky.klkakao.delegates.ViewInteractionDelegate
import org.hamcrest.Matcher

open class ViewInteractionDelegateImpl(
    private val viewInteraction: ViewInteraction
) : ViewInteractionDelegate {

    private var isCustomFailureHandlerSet = false

    override fun perform(viewAction: ViewAction): ViewInteractionDelegate {

        val viewActionProxy = ViewActionProxy(
            viewAction,
            Configurator.viewActionInterceptors
        )

        execute { viewInteraction.perform(viewActionProxy) }

        return this
    }

    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            Configurator.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }

        return this
    }

    override fun check(
        function: (View, NoMatchingViewException?) -> Unit
    ): ViewInteractionDelegate {

        val viewAssertionProxy = ViewAssertionProxy(
            ViewAssertion(function),
            Configurator.viewAssertionInterceptors
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

        return Configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }

    private fun setFailureHandlerIfNecessary() {

        Configurator.failureInterceptor?.let { failureInterceptor ->
            if (!isCustomFailureHandlerSet) {
                withFailureHandler(failureInterceptor::interceptAndThrow)
            }
        }
    }
}