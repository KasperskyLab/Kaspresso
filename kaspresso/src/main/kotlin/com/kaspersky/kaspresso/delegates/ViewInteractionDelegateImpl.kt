package com.kaspersky.kaspresso.delegates

import android.view.View
import android.support.test.espresso.*
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy
import com.kaspersky.klkakao.delegates.ViewInteractionDelegate
import org.hamcrest.Matcher

/**
 * An implementation of [ViewInteractionDelegate], that delegates the [ViewInteraction]'s interface calls
 * to [ViewActionProxy] and [ViewAssertionProxy].
 */
open class ViewInteractionDelegateImpl(
    private val viewInteraction: ViewInteraction
) : ViewInteractionDelegate {

    /**
     * Creates [ViewActionProxy] instance and delegates [ViewInteraction.perform] call to it.
     *
     * @param viewAction an action to execute.
     * @return an existing [ViewInteractionDelegateImpl] instance.
     */
    override fun perform(viewAction: ViewAction): ViewInteractionDelegate {
        val viewActionProxy = ViewActionProxy(
            viewAction,
            Configurator.viewActionInterceptors
        )

        execute { viewInteraction.perform(viewActionProxy) }
        return this
    }

    /**
     * Creates [ViewAssertionProxy] instance and delegates [ViewInteraction.check] call to it.
     *
     * @param viewAssertion an assertion to execute.
     * @return an existing [ViewInteractionDelegateImpl] instance.
     */
    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            Configurator.viewAssertionInterceptors
        )

        execute { viewInteraction.check(viewAssertionProxy) }
        return this
    }

    /**
     * Creates [ViewAssertionProxy] instance and delegates [ViewInteraction.check] call to it.
     *
     * @param function a parameter with which the [ViewAssertion] will be created.
     * @return an existing [ViewInteractionDelegateImpl] instance.
     */
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

    /**
     * Calls [ViewInteraction.withFailureHandler] on wrapped [viewInteraction] and turns [isCustomFailureHandlerSet]
     * flag on.
     *
     * @param function a function that will be used as [FailureHandler].
     * @return an existing [ViewInteractionDelegateImpl] instance.
     */
    override fun withFailureHandler(
        function: (Throwable, Matcher<View>) -> Unit
    ): ViewInteractionDelegate {
        viewInteraction.withFailureHandler(function)
        return this
    }

    /**
     * Calls [ViewInteraction.inRoot] on [viewInteraction].
     *
     * @param rootMatcher a rootMatcher.
     * @return an existing [ViewInteractionDelegateImpl] instance.
     */
    override fun inRoot(rootMatcher: Matcher<Root>): ViewInteractionDelegate {
        viewInteraction.inRoot(rootMatcher)
        return this
    }

    /**
     * Delegates the executable invocation to [Configurator.executingInterceptor] if it exists, otherwise just invoke
     * it by itself.
     *
     * @param executable a function to invoke.
     * @return [ViewInteraction] as it is a result of executable invocation.
     */
    private fun execute(executable: () -> ViewInteraction): ViewInteraction {
        return Configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }
}