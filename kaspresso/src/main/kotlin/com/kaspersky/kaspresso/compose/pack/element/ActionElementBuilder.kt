package com.kaspersky.kaspresso.compose.pack.element

/**
 * Builder of ActionElement's using in compose
 */
class ActionElementBuilder<ElementType>(
    private val element: ElementType,
    private val action: () -> Unit
) {

    private var postAction: (() -> Unit)? = null

    infix fun then(action: () -> Unit) {
        if (postAction == null) {
            postAction = action
            return
        }
        throw ComposeBuilderException(
            "Please, use compose functionality correctly! " +
                    "Set `then` section only for one `or`!"
        )
    }

    fun build(): ActionElement<ElementType> =
        ActionElement(
            element = element,
            action = action,
            postAction = postAction
        )

}