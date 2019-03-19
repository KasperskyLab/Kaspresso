package com.kaspersky.kaspresso.delegates

import android.support.test.espresso.web.assertion.WebAssertionProxy
import com.kaspersky.kaspresso.proxy.AtomProxy
import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.agoda.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class WebInteractionDelegateImpl(
    private val webInteraction: Web.WebInteraction<*>
) : WebInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return WebInteractionDelegateImpl(webInteraction.withElement(ref))
    }

    override fun perform(webAction: Atom<*>): WebInteractionDelegate {

        val webActionProxy = AtomProxy(
            webAction,
            Configurator.atomInterceptors
        )

        return WebInteractionDelegateImpl(
            execute { webInteraction.perform(webActionProxy) }
        )
    }

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

    private fun execute(executable: () -> Web.WebInteraction<*>): Web.WebInteraction<*> {

        return Configurator.executingInterceptor
            ?.interceptAndExecuteWeb { executable.invoke() }
            ?: executable.invoke()
    }
}