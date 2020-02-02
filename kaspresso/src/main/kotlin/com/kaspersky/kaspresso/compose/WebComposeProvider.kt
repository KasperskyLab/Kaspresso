package com.kaspersky.kaspresso.compose

import com.agoda.kakao.web.WebElementBuilder
import com.kaspersky.kaspresso.compose.pack.ActionsOnWebElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack

/**
 * The interface to provide the composing actions and assertions on web views functionality.
 */
interface WebComposeProvider {

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.compose(timeoutMs: Long? = null, block: ActionsOnWebElementsPack.() -> Unit)

    /**
     * Composes a [block] of actions on the given web view in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.KWebInteraction.compose(
        timeoutMs: Long? = null,
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )
}