package com.kaspersky.kaspresso.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.view.View
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy
import com.kaspersky.klkakao.delegates.DataInteractionDelegate
import com.kaspersky.klkakao.delegates.ViewInteractionDelegate
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

/**
 * An implementation of [DataInteractionDelegate], that delegates the [DataInteraction]'s interface calls
 * to [ViewAssertionProxy].
 */
open class DataInteractionDelegateKaspressoImpl(
    override val viewInteraction: ViewInteraction,
    override val dataInteraction: DataInteraction,
    private val configurator: Configurator
) : DataInteractionDelegate {

    /**
     * Calls [DataInteraction.onChildView] on wrapped [dataInteraction].
     *
     * @param childMatcher an childMatcher.
     * @return an existing [DataInteractionDelegateKaspressoImpl] instance.
     */
    @CheckResult
    @CheckReturnValue
    override fun onChildView(childMatcher: Matcher<View>): DataInteractionDelegate {
        dataInteraction.onChildView(childMatcher)
        return this
    }

    /**
     * Creates [ViewAssertionProxy] instance and delegates [DataInteraction.check] call to it.
     *
     * @param viewAssertion an assertion to execute.
     * @return a new instance of [DataInteractionDelegateKaspressoImpl]
     */
    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            configurator.viewAssertionInterceptors
        )

        return ViewInteractionDelegateKaspressoImpl(
            execute { dataInteraction.check(viewAssertionProxy) },
            configurator
        )
    }

    /**
     * Delegates the executable invocation to [Configurator.executingInterceptor] if it exists, otherwise just invoke
     * it by itself.
     *
     * @param executable a function to invoke.
     * @return [ViewInteraction] as it is a result of executable invocation.
     */
    private fun execute(executable: () -> ViewInteraction): ViewInteraction {
        return configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }
}