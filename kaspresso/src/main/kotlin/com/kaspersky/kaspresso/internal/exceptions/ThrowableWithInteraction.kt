package com.kaspersky.kaspresso.internal.exceptions

class ThrowableWithInteraction(
    val throwable: Throwable,
    val interaction: Any
) : Throwable(null, throwable.cause)