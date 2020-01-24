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
import com.kaspersky.kaspresso.compose.pack.element.ActionElementBuilder
import com.kaspersky.kaspresso.compose.pack.element.ActionElement

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack {

    private val actionElementBuilders: MutableList<ActionElementBuilder<out Any>> = mutableListOf()

    /**
     * Adds the [element] of type [Type] and the [action] to [actionElementBuilders] and [action] for future composing
     * where [Type] is bounding by KBaseView (Kakao)
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <Type> or(element: Type, action: Type.() -> Unit): ActionElementBuilder<Type>
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction>
    {
        val builder = ActionElementBuilder(element, { action.invoke(element) })
        actionElementBuilders += builder
        return builder
    }

    /**
     * Adds the [element] of type [Type] and the [action] to [actionElementBuilders] and [action] for future composing
     * where [Type] is bounding by UiBaseView (Kautomator)
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <Type> or(element: Type, action: Type.() -> Unit): ActionElementBuilder<Type>
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction>
    {
        val builder = ActionElementBuilder(element, { action.invoke(element) })
        actionElementBuilders += builder
        return builder
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ActionElement<out Any>> {
        require(actionElementBuilders.isNotEmpty()) { "Nothing to compose" }

        val actionElements = mutableListOf<ActionElement<out Any>>()
        actionElementBuilders.forEach {
            actionElements += it.build()
        }

        return actionElements
    }
}