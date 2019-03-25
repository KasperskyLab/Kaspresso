package com.kaspersky.kaspresso.delegates

import android.support.test.espresso.web.assertion.WebAssertionProxy
import com.kaspersky.kaspresso.proxy.AtomProxy
import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.klkakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

/**
 * An implementation of [WebInteractionDelegate], that delegates the [Web.WebInteraction]'s interface calls
 * to [AtomProxy] and [WebAssertionProxy].
 */
open class WebInteractionDelegateImpl(
    private val webInteraction: Web.WebInteraction<*>
) : WebInteractionDelegate {

    /**
     * Calls [Web.WebInteraction.withElement] on wrapped [webInteraction].
     *
     * @return an new instance of [WebInteractionDelegateImpl].
     */
    @CheckResult
    @CheckReturnValue
    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return WebInteractionDelegateImpl(webInteraction.withElement(ref))
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
            Configurator.atomInterceptors
        )

        return WebInteractionDelegateImpl(
            execute { webInteraction.perform(webActionProxy) }
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
            Configurator.webAssertionInterceptors
        )

        return WebInteractionDelegateImpl(
            execute { webInteraction.check(webAssertionProxy) }
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
        return Configurator.executingInterceptor
            ?.interceptAndExecuteWeb { executable.invoke() }
            ?: executable.invoke()
    }
}