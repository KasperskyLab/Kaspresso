package com.kaspersky.uitest_framework.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.support.test.espresso.web.model.Atom
import com.kaspersky.uitest_framework.proxy.AtomProxy
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class WebInteractionDelegateImpl(
        override val webInteraction: Web.WebInteraction<*>
): WebInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return WebInteractionDelegateImpl(webInteraction.withElement(ref))
    }

    override fun perform(webAction: Atom<*>): WebInteractionDelegate {

        val webActionProxy = AtomProxy(
                webAction,
                Configuration.atomInterceptors
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
                Configuration.webAssertionInterceptors
        )

        return WebInteractionDelegateImpl(
                execute { webInteraction.check(webAssertionProxy) }
        )
    }

    private fun execute(executable: () -> Web.WebInteraction<*>): Web.WebInteraction<*> {

        return Configuration.executingInterceptor
                ?.interceptAndExecuteWeb { executable.invoke() }
                ?: executable.invoke()
    }
}