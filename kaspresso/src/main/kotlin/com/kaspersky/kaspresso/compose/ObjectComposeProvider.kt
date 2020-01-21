package com.kaspersky.kaspresso.compose

import com.kaspersky.components.kautomator.dsl.common.views.UiBaseView
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack

interface ObjectComposeProvider {

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    fun composeExternal(block: ActionsOnElementsPack<UiBaseView<*>>.() -> Unit)

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    fun <T : UiBaseView<*>> T.composeExternal(block: ActionsPack<T>.() -> Unit)

}