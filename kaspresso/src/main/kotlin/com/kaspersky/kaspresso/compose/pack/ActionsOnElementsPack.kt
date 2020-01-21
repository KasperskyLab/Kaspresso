package com.kaspersky.kaspresso.compose.pack

import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack<ElementType> {

    private val elements: MutableList<ElementType> = mutableListOf()
    private val actions: MutableList<() -> Unit> = mutableListOf()

    /**
     * Adds the [element] of type [T] and the [action] to [elements] and [actions] for future composing.
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <T : ElementType> or(element: T, action: T.() -> Unit) {

        elements += element
        actions += { action.invoke(element) }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): Pair<List<ElementType>, List<() -> Unit>> {
        if (elements.isEmpty() || actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return Pair(elements, actions)
    }
}