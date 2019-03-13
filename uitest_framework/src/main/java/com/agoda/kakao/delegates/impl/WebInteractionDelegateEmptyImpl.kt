package com.agoda.kakao.delegates.impl

import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import android.support.test.espresso.web.sugar.Web
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.WebInteractionDelegate
import org.hamcrest.Matcher

class WebInteractionDelegateEmptyImpl internal constructor(
        override val webInteraction: Web.WebInteraction<*>
): WebInteractionDelegate {

    override fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate {
        return KakaoConfigurator.createWebInteractionDelegate(
                webInteraction.withElement(ref)
        )
    }

    override fun perform(webAction: Atom<*>): WebInteractionDelegate {
        return KakaoConfigurator.createWebInteractionDelegate(
                webInteraction.perform(webAction)
        )
    }

    override fun <E> check(
            webAssertion: WebAssertion<E>,
            atom: Atom<E>,
            matcher: Matcher<E>
    ): WebInteractionDelegate {
        return KakaoConfigurator.createWebInteractionDelegate(
                webInteraction.check(webAssertion)
        )
    }
}