package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import java.lang.IllegalArgumentException

class ActionsOnElementsPack {

    private val elements: MutableList<Interceptable<ViewInteraction, ViewAssertion, ViewAction>> = mutableListOf()
    private val actions: MutableList<() -> Unit> = mutableListOf()

    fun <T> or(element: T, action: T.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        elements += element
        actions += { action.invoke(element) }
    }

    internal fun build(): Pair<List<Interceptable<ViewInteraction, ViewAssertion, ViewAction>>, List<() -> Unit>> {
        if (elements.isEmpty() || actions.isEmpty()) throw IllegalArgumentException("Nothing to compose")
        return Pair(elements, actions)
    }
}