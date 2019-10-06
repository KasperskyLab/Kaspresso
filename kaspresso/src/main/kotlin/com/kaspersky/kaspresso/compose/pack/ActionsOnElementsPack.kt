package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import java.lang.IllegalArgumentException

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack {
    private val elements: MutableList<Interceptable<ViewInteraction, ViewAssertion, ViewAction>> = mutableListOf()
    private val actions: MutableList<() -> Unit> = mutableListOf()

    /**
     * Adds the [element] of type [T] and the [action] to [elements] and [actions] for future composing.
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <T> or(element: T, action: T.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        elements += element
        actions += { action.invoke(element) }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): Pair<List<Interceptable<ViewInteraction, ViewAssertion, ViewAction>>, List<() -> Unit>> {
        if (elements.isEmpty() || actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return Pair(elements, actions)
    }
}