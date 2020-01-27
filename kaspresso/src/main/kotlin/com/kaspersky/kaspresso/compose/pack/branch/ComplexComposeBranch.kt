package com.kaspersky.kaspresso.compose.pack.branch

/**
 * The base data class using in compose
 */
data class ComplexComposeBranch<ElementType>(
    val element: ElementType,
    val check: () -> Unit,
    val postAction: (() -> Unit)?
)