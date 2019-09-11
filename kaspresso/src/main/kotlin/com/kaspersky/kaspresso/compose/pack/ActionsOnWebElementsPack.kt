package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.WebElementBuilder
import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
 */
class ActionsOnWebElementsPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val actions: MutableList<() -> Unit> = mutableListOf()

    /**
     * Builds the lambda to add to [actions] that invokes the given [action] on the web element built by
     * [webElementBuilder] with given [locator] and [value].
     *
     * @param locator the locator type of web view element.
     * @param value the value to be searched for in web view.
     * @param action actions or assertions on the interacted view.
     */
    fun orWithElement(locator: Locator, value: String, action: WebElementBuilder.KWebInteraction.() -> Unit) {
        webElementBuilder.withElement(locator, value) {
            actions += { action.invoke(this) }
        }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
     */
    internal fun build(): List<() -> Unit> {
        if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actions
    }
}