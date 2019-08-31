package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.intercept.Interceptable
import com.agoda.kakao.web.WebActions
import com.agoda.kakao.web.WebAssertions

interface WebComposeProvider {

    fun <T> T.compose(
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        block: ActionsPack<T>.() -> Unit
    ): Unit where T : WebActions, T : WebAssertions
}