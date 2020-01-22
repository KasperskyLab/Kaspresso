package com.kaspersky.kaspresso.compose.pack

import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack<ElementType> {

    private val actionElements: MutableList<ActionElement<out ElementType>> = mutableListOf()

    /**
     * Adds the [element] of type [T] and the [action] to [actionElements] and [actions] for future composing.
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <T : ElementType> or(element: T, action: T.() -> Unit): Beta {
        actionElements += ActionElement(element, { action.invoke(element) })
        return Beta()
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ActionElement<out ElementType>> {
        if (actionElements.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actionElements
    }
}

data class ActionElement<ElementType>(
    val element: ElementType,
    val actions: () -> Unit
)

class Beta {

    infix fun then(action: () -> Unit) {
        action.invoke()
    }



}