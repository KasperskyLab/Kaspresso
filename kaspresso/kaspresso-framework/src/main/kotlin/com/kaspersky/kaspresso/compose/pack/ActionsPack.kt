package com.kaspersky.kaspresso.compose.pack

import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranch
import com.kaspersky.kaspresso.compose.pack.branch.ComplexComposeBranchBuilder

/**
 * The builder class for parameters of [com.kaspersky.kaspresso.compose.ComposeProvider.compose] and
 * [com.kaspersky.kaspresso.compose.WebComposeProvider.compose]] methods.
 */
class ActionsPack<T>(
    private val element: T
) {

    private val complexComposeBranchBuilders: MutableList<ComplexComposeBranchBuilder<out T>> = mutableListOf()

    /**
     * Builds the lambda to add to [actions] that invokes the given [action] on the interacted view of type [T].
     *
     * @param action actions or assertions on the interacted view.
     */
    fun or(action: T.() -> Unit): ComplexComposeBranchBuilder<T> {
        return ComplexComposeBranchBuilder(element, { action.invoke(element) })
            .also { complexComposeBranchBuilders += it }
    }

    /**
     * @return the built parameters for [com.kaspersky.kaspresso.compose.ComposeProvider.compose] method.
     */
    internal fun build(): List<ComplexComposeBranch<out T>> {
        require(complexComposeBranchBuilders.isNotEmpty()) { "Nothing to compose" }

        val composeBranches = mutableListOf<ComplexComposeBranch<out T>>()
        complexComposeBranchBuilders.forEach {
            composeBranches += it.build()
        }

        return composeBranches
    }
}
