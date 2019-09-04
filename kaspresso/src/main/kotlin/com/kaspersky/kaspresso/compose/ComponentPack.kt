package com.kaspersky.kaspresso.compose

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.WebElementBuilder
import java.lang.IllegalArgumentException

data class Component<T>(
    val element: T,
    val action: T.() -> Unit
)

data class WebComponent(
    val webElement: WebElementBuilder.KWebInteraction,
    val webElementBuilder: WebElementBuilder,
    val webAction: WebElementBuilder.KWebInteraction.() -> Unit
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

class WebComponentPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val webComponents: MutableList<WebComponent> = arrayListOf()

    fun orWithElement(
        locator: Locator,
        value: String,
        webAction: WebElementBuilder.KWebInteraction.() -> Unit
    ) {
        webElementBuilder.withElement(locator, value) {
            webComponents += WebComponent(this, webElementBuilder, webAction)
        }
    }

    internal fun build(): MutableList<WebComponent> {
        if (webComponents.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return webComponents
    }
}