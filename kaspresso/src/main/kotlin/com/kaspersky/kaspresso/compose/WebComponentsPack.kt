package com.kaspersky.kaspresso.compose

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.WebElementBuilder
import java.lang.IllegalArgumentException

data class WebComponent(
    val webElement: WebElementBuilder.KWebInteraction,
    val webElementBuilder: WebElementBuilder,
    val webAction: WebElementBuilder.KWebInteraction.() -> Unit
)

class WebComponentPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val webComponents: MutableList<WebComponent> = arrayListOf()

    fun orWithElement(locator: Locator, value: String, webAction: WebElementBuilder.KWebInteraction.() -> Unit) {
        webElementBuilder.withElement(locator, value) {
            webComponents += WebComponent(this, webElementBuilder, webAction)
        }
    }

    internal fun build(): MutableList<WebComponent> {
        if (webComponents.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return webComponents
    }
}