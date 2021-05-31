package com.kaspersky.kaspresso.compose.pack

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import io.github.kakaocup.kakao.common.actions.BaseActions
import io.github.kakaocup.kakao.common.assertions.BaseAssertions
import io.github.kakaocup.kakao.intercept.Interceptable
import com.kaspersky.components.kautomator.component.common.actions.UiBaseActions
import com.kaspersky.components.kautomator.component.common.assertions.UiBaseAssertions
import com.kaspersky.components.kautomator.intercept.base.UiInterceptable
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranchBuilder

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
 */
class ActionsOnElementsPack {

    private val complexComposeBranchBuilders: MutableList<ComplexComposeBranchBuilder<out Any>> = mutableListOf()

    /**
     * Adds the [element] of type [Type] and the [action] to [complexComposeBranchBuilders] and [action] for future composing
     * where [Type] is bounding by KBaseView (Kakao)
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <Type> or(element: Type, action: Type.() -> Unit): ComplexComposeBranchBuilder<Type>
            where Type : BaseActions, Type : BaseAssertions,
                  Type : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {
        return ComplexComposeBranchBuilder(element, { action.invoke(element) })
            .also { complexComposeBranchBuilders += it }
    }

    /**
     * Adds the [element] of type [Type] and the [action] to [complexComposeBranchBuilders] and [action] for future composing
     * where [Type] is bounding by UiBaseView (Kautomator)
     *
     * @param element the interacted view.
     * @param action actions or assertions on the interacted view.
     */
    fun <Type> or(element: Type, action: Type.() -> Unit): ComplexComposeBranchBuilder<Type>
            where Type : UiBaseActions, Type : UiBaseAssertions,
                  Type : UiInterceptable<UiObjectInteraction, UiObjectAssertion, UiObjectAction> {
        return ComplexComposeBranchBuilder(element, { action.invoke(element) })
            .also { complexComposeBranchBuilders += it }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ComplexComposeBranch<out Any>> {
        require(complexComposeBranchBuilders.isNotEmpty()) { "Nothing to compose" }

        val composeBranches = mutableListOf<ComplexComposeBranch<out Any>>()
        complexComposeBranchBuilders.forEach {
            composeBranches += it.build()
        }

        return composeBranches
    }
}
