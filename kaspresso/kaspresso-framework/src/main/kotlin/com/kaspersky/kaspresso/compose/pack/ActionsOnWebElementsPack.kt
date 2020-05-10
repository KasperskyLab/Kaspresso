package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranchBuilder
import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
 */
class ActionsOnWebElementsPack(
    private val webElementBuilder: WebElementBuilder
) {
    private val complexComposeBranchBuilders: MutableList<ComplexComposeBranchBuilder<WebElementBuilder.KWebInteraction>> = mutableListOf()

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
            ComplexComposeBranchBuilder(this, { action.invoke(this) })
                .also { complexComposeBranchBuilders += it }
        }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.WebComposeProvider.compose] method.
     */
    internal fun build(): List<ComplexComposeBranch<WebElementBuilder.KWebInteraction>> {
        require(complexComposeBranchBuilders.isNotEmpty()) { "Nothing to compose" }

        val composeBranches = mutableListOf<ComplexComposeBranch<WebElementBuilder.KWebInteraction>>()
        complexComposeBranchBuilders.forEach {
            composeBranches += it.build()
        }

        return composeBranches
    }
}
