package com.kaspersky.kaspresso.compose.pack

import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] and
 * [com.kaspersky.kaspresso.compose.WebComposeProvider.compose]] methods.
 */
class ActionsPack<T>(
    private val element: T
) {
    private val actions: MutableList<() -> Unit> = arrayListOf()

    /**
     * Builds the lambda to add to [actions] that invokes the given [action] on the interacted view of type [T].
     *
     * @param action actions or assertions on the interacted view.
     */
    fun or(action: T.() -> Unit) {
        actions += { action.invoke(element) }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<() -> Unit> {
        if (actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actions
    }
}
