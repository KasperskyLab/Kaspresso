package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.WebElementBuilder
import java.lang.IllegalArgumentException

class ActionsOnWebElementsPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val actions: MutableList<() -> Unit> = mutableListOf()

    fun orWithElement(locator: Locator, value: String, action: WebElementBuilder.KWebInteraction.() -> Unit) {
        webElementBuilder.withElement(locator, value) {
            actions += { action.invoke(this) }
        }
    }

    internal fun build(): List<() -> Unit> {
        if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actions
    }
}