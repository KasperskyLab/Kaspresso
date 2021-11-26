package com.kaspersky.components.composesupport.config

import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * Kaspresso Builder that includes all appropriate interceptors to support Jetpack Compose.
 */
fun Kaspresso.Builder.Companion.withComposeSupport(
    customize: Kaspresso.Builder.() -> Unit = {},
    lateComposeCustomize: Kaspresso.Builder.(ComposeConfig.Builder) -> Unit = {}
): Kaspresso.Builder = simple(customize).addComposeSupport(lateComposeCustomize)

/**
 * Kaspresso Builder that includes all appropriate interceptors to support Jetpack Compose.
 */
fun Kaspresso.Builder.addComposeSupport(
    lateComposeCustomize: Kaspresso.Builder.(ComposeConfig.Builder) -> Unit = {}
): Kaspresso.Builder = apply {
    ComposeConfig.Builder.default(
        kaspressoBuilder = this,
        lateComposeCustomize = lateComposeCustomize
    ).build()
}
