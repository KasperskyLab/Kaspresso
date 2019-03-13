package com.agoda.kakao.delegates.impl

import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.agoda.kakao.configuration.Configurator
import com.agoda.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher

class WebInteractionDelegateEmptyImpl internal constructor(
        override val webInteraction: Web.WebInteraction<*>
): WebInteractionDelegate {

    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return Configurator.webInteractionDelegateFactory.invoke(
                webInteraction.withElement(ref)
        )
    }

    override fun perform(webAction: Atom<*>): WebInteractionDelegate {
        return Configurator.webInteractionDelegateFactory.invoke(
                webInteraction.perform(webAction)
        )
    }

    override fun <E> check(
            webAssertion: WebAssertion<E>,
            atom: Atom<E>,
            matcher: Matcher<E>
    ): WebInteractionDelegate {
        return Configurator.webInteractionDelegateFactory.invoke(
                webInteraction.check(webAssertion)
        )
    }
}