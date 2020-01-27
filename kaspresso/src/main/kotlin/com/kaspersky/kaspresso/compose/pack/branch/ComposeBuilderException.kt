package com.kaspersky.kaspresso.compose.pack.branch

import com.kaspersky.kaspresso.compose.ComposeProviderImpl

/**
 * Exception throwing in case of incorrect [ComposeProviderImpl.compose] expression building
 */
class ComposeBuilderException(override val message: String) : RuntimeException(message)