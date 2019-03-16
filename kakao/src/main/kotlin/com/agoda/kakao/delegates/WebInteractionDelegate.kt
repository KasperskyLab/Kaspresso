package com.agoda.kakao.delegates

import android.support.annotation.CheckResult
import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.model.ElementReference
import org.hamcrest.Matcher
import javax.annotation.CheckReturnValue

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