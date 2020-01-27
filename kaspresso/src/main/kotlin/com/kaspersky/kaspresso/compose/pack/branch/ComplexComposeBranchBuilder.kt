package com.kaspersky.kaspresso.compose.pack.branch

/**
 * Builder of ComplexComposeBranch's using in compose
 */
class ComplexComposeBranchBuilder<ElementType>(
    private val element: ElementType,
    private val check: () -> Unit
) {

    private var postAction: (() -> Unit)? = null

    infix fun then(postAction: () -> Unit) {
        if (this.postAction == null) {
            this.postAction = postAction
            return
        }
        throw ComposeBuilderException(
            "Please, use compose functionality correctly! " +
                    "Keep the rule: one `or` <=> one `then`!"
        )
    }

    fun build(): ComplexComposeBranch<ElementType> =
        ComplexComposeBranch(
            element = element,
            check = check,
            postAction = postAction
        )
}