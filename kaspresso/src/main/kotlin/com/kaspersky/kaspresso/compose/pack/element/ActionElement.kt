package com.kaspersky.kaspresso.compose.pack.element

data class ActionElement<ElementType>(
    val element: ElementType,
    val action: () -> Unit,
    val postAction: (() -> Unit)?
)