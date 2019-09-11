package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.AtomProxy

/**
 * Kaspresso's implementation of Kakao's web interaction interceptor.
 */
internal class KakaoWebInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(kaspresso) {

    /**
     * Folds all [Web.WebInteraction]'s one into another with the actual [interaction] "check" call as the initial,
     * and invokes the resulting lambda.
     */
    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        kaspresso.webBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), kaspresso.webAssertionWatcherInterceptors)
                )
            },
            operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                { webBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [Web.WebInteraction]'s one into another with the actual [interaction] "perform" call as the initial,
     * and invokes the resulting lambda.
     */
    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        kaspresso.webBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    AtomProxy(action, interaction.getMatcher(), kaspresso.atomWatcherInterceptors)
                )
            },
            operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                { webBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}