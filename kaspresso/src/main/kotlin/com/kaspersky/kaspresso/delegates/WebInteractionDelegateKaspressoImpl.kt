package com.kaspersky.kaspresso.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.agoda.kakao.delegates.WebInteractionDelegate
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.proxy.AtomProxy
import javax.annotation.CheckReturnValue
import org.hamcrest.Matcher

/**
 * An implementation of [WebInteractionDelegate], that delegates the [Web.WebInteraction]'s interface calls
 * to [AtomProxy] and [WebAssertionProxy].
 */
open class WebInteractionDelegateKaspressoImpl(
    override val webInteraction: Web.WebInteraction<*>,
    private val configurator: Configurator
) : WebInteractionDelegate {

    /**
     * Calls [Web.WebInteraction.withElement] on wrapped [webInteraction].
     *
     * @return an new instance of [WebInteractionDelegateKaspressoImpl].
     */
    @CheckResult
    @CheckReturnValue
    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        val withElementProxy = AtomProxy(
            ref,
            configurator.atomInterceptors
        )

        return WebInteractionDelegateKaspressoImpl(
            execute { webInteraction.withElement(withElementProxy) },
            configurator
        )
    }

    /**
     * Creates [AtomProxy] instance and delegates [Web.WebInteraction.perform] call to it.
     *
     * @param webAction an action to execute.
     * @return a new instance of [WebInteractionDelegate].
     */
    override fun perform(webAction: Atom<*>): WebInteractionDelegate {
        val webActionProxy = AtomProxy(
            webAction,
            configurator.atomInterceptors
        )

        return WebInteractionDelegateKaspressoImpl(
            execute { webInteraction.perform(webActionProxy) },
            configurator
        )
    }

    /**
     * Creates [WebAssertionProxy] instance and delegates [Web.WebInteraction.check] call to it.
     *
     * @param webAssertion an assertion to execute.
     * @param atom an atom to pass to [WebAssertionProxy].
     * @param matcher a matcher to pass to [WebAssertionProxy].
     * @return a new instance of [WebInteractionDelegate].
     */
    override fun <E> check(
        webAssertion: WebAssertion<E>,
        atom: Atom<E>,
        matcher: Matcher<E>
    ): WebInteractionDelegate {

        val webAssertionProxy = WebAssertionProxy(
            webAssertion,
            atom,
            matcher,
            configurator.webAssertionInterceptors
        )

        return WebInteractionDelegateKaspressoImpl(
            execute { webInteraction.check(webAssertionProxy) },
            configurator
        )
    }

    /**
     * Delegates the executable invocation to [Configurator.executingInterceptor] if it exists, otherwise just invoke
     * it by itself.
     *
     * @param executable a function to invoke.
     * @return [Web.WebInteraction] as it is a result of executable invocation.
     */
    private fun execute(executable: () -> Web.WebInteraction<*>): Web.WebInteraction<*> {
        return configurator.executingInterceptor
            ?.interceptAndExecuteWeb { executable.invoke() }
            ?: executable.invoke()
    }
}