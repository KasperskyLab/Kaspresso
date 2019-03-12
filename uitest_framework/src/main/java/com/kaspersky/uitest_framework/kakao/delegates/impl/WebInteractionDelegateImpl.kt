package com.kaspersky.uitest_framework.kakao.delegates.impl

import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.uitest_framework.kakao.delegates.factory.DelegatesFactory
import com.kaspersky.uitest_framework.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher

class WebInteractionDelegateImpl internal constructor(
        override val webInteraction: Web.WebInteraction<*>
): WebInteractionDelegate {

    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return DelegatesFactory.webInteractionDelegateFactory.invoke(
                webInteraction.withElement(ref)
        )
    }

    override fun perform(webAction: Atom<*>): WebInteractionDelegate {
        return DelegatesFactory.webInteractionDelegateFactory.invoke(
                webInteraction.perform(webAction)
        )
    }

    override fun <E> check(
            webAssertion: WebAssertion<E>,
            atom: Atom<E>,
            matcher: Matcher<E>
    ): WebInteractionDelegate {
        return DelegatesFactory.webInteractionDelegateFactory.invoke(
                webInteraction.check(webAssertion)
        )
    }
}