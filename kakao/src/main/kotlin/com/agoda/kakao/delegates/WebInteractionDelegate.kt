package com.agoda.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

/**
 * An interface duplicating the used in Kakao part of web interaction interface.
 * If you want to extend the base web interaction behavior, you should implement this interface, and specify it's
 * factory in [com.agoda.kakao.configurator.KakaoConfigurator]
 */
interface WebInteractionDelegate {

    @CheckResult
    @CheckReturnValue
    fun withElement(ref: Atom<ElementReference>): WebInteractionDelegate

    fun perform(webAction: Atom<*>): WebInteractionDelegate

    fun <E> check(
        webAssertion: WebAssertion<E>,
        atom: Atom<E>,
        matcher: Matcher<E>
    ): WebInteractionDelegate
}