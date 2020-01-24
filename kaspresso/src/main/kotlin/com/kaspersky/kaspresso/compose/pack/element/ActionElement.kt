package com.kaspersky.kaspresso.compose.pack.element

/**
 * The base data class using in compose
 */
data class ActionElement<ElementType>(
    val element: ElementType,
    val action: () -> Unit,
    val postAction: (() -> Unit)?
)