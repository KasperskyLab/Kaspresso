package com.kaspersky.kaspresso.failure.exceptions

import java.lang.RuntimeException

class ActionNotSupportedInSharedTestException(val componentName: String): RuntimeException(
    """

        $componentName is not supported in 'sharedTest'. Any call to any of its methods will fail with this exception.
        We suggest that you disable 'sharedTest' by inheriting from TestCase(Kaspresso.Builder.simple(sharedTest = false)) instead.

    """.trimIndent()

)
