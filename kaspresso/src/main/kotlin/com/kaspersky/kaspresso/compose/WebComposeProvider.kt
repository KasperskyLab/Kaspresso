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
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun WebElementBuilder.compose(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsOnWebElementsPack.() -> Unit
    )

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.unsafeCompose(block: ActionsOnWebElementsPack.() -> Unit)

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing flakySafely!
     *
     * @param timeoutMs the timeout during which attempts to execute `or` sections will be made.
     * @param intervalMs the interval at which attempts to execute `or` sections will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of `or` sections execution.
     * @param block the actions to compose.
     */
    fun WebElementBuilder.KWebInteraction.compose(
        webElementBuilder: WebElementBuilder,
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )

    /**
     * Composes a [block] of actions with their web views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     * Please, be aware of `or` sections are executing without flakySafely mechanism
     *     even though there may be flakySafely interceptors in your Kaspresso settings!
     *
     * @param block the actions to compose.
     */
    fun WebElementBuilder.KWebInteraction.unsafeCompose(
        webElementBuilder: WebElementBuilder,
        block: ActionsPack<WebElementBuilder.KWebInteraction>.() -> Unit
    )
}