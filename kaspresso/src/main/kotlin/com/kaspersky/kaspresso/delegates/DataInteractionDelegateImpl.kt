package com.kaspersky.kaspresso.delegates

import android.view.View
import android.support.annotation.CheckResult
import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
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
open class DataInteractionDelegateImpl(
    private val dataInteraction: DataInteraction
) : DataInteractionDelegate {

    /**
     * Calls [DataInteraction.onChildView] on wrapped [dataInteraction].
     *
     * @param childMatcher an childMatcher.
     * @return an existing [DataInteractionDelegateImpl] instance.
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
     * @return a new instance of [DataInteractionDelegateImpl]
     */
    override fun check(viewAssertion: ViewAssertion): ViewInteractionDelegate {
        val viewAssertionProxy = ViewAssertionProxy(
            viewAssertion,
            Configurator.viewAssertionInterceptors
        )

        return ViewInteractionDelegateImpl(
            execute { dataInteraction.check(viewAssertionProxy) }
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
        return Configurator.executingInterceptor
            ?.interceptAndExecute { executable.invoke() }
            ?: executable.invoke()
    }
}