package com.kaspersky.kaspresso.testcases.core.testcontext.provide.compose

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.agoda.kakao.intercept.Interceptable
import java.lang.IllegalArgumentException

data class Component<T>(
    val element: T,
    val action: T.() -> Unit
)

data class WebComponent<T>(
    val element: T,
    val interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
    val action: T.() -> Unit
)

class ComponentPack<T> {

    private val components: MutableList<Component<T>> = arrayListOf()

    fun or(element: T, action: T.() -> Unit) {
        components += Component(element, action)
    }

    internal fun build(): MutableList<Component<T>> {
        if (components.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return components
    }
}

class WebComponentPack<T> {

    private val webComponents: MutableList<WebComponent<T>> = arrayListOf()

    fun or(
        element: T,
        interceptable: Interceptable<Web.WebInteraction<*>, WebAssertion<*>, Atom<*>>,
        action: T.() -> Unit
    ) {
        webComponents += WebComponent(element, interceptable, action)
    }

    internal fun build(): MutableList<WebComponent<T>> {
        if (webComponents.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return webComponents
    }
}