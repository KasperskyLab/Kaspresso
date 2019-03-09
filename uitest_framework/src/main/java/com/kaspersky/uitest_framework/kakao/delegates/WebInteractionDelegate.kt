package com.kaspersky.uitest_framework.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.support.test.espresso.web.model.Atom
import com.kaspersky.uitest_framework.kakao.proxy.AtomProxy
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

open class WebInteractionDelegate(
        private val webInteraction: Web.WebInteraction<*>
) {
    @CheckResult
    @CheckReturnValue
    open fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return WebInteractionDelegate(webInteraction.withElement(ref))
    }

    open fun perform(webAction: Atom<*>): WebInteractionDelegate {

        val webActionProxy = AtomProxy(
                webAction,
                InterceptorsHolder.atomInterceptors
        )

        return WebInteractionDelegate(
                execute { webInteraction.perform(webActionProxy) }
        )
    }

    open fun <E> check(
            webAssertion: WebAssertion<E>,
            atom: Atom<E>,
            matcher: Matcher<E>
    ): WebInteractionDelegate {

        val webAssertionProxy = WebAssertionProxy(
                webAssertion,
                atom,
                matcher,
                InterceptorsHolder.webAssertionInterceptors
        )

        return WebInteractionDelegate(
                execute { webInteraction.check(webAssertionProxy) }
        )
    }

    private fun execute(executable: () -> Web.WebInteraction<*>): Web.WebInteraction<*> {

        return InterceptorsHolder.executingInterceptor
                ?.interceptAndExecuteWeb { executable.invoke() }
                ?: executable.invoke()
    }
}