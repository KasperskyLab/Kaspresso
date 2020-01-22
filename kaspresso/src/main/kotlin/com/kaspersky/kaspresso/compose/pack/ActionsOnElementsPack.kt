package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.dsl.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.dsl.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptable
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack {

    private val actionElements: MutableList<ActionElement<out Any>> = mutableListOf()

    /**
     * Adds the [element] of type [T] and the [action] to [actionElements] and [actions] for future composing.
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <Type> or(element: Type, action: Type.() -> Unit): Beta
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>
    {
        actionElements += ActionElement(element, { action.invoke(element) })
        return Beta()
    }

    fun <Type> or(element: Type, action: Type.() -> Unit): Beta
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>
    {
        actionElements += ActionElement(element, { action.invoke(element) })
        return Beta()
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ActionElement<out Any>> {
        if (actionElements.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return actionElements
    }
}

data class ActionElement<ElementType> (
    val element: ElementType,
    val actions: () -> Unit
)

class Beta {

    infix fun then(action: () -> Unit) {
        action.invoke()
    }



}