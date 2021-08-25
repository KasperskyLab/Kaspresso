package com.kaspersky.kaspresso.failure.exceptions

import java.lang.RuntimeException

class KautomatorOnSharedTestException : RuntimeException(
    """

        You are using Kautomator UiScreen for a 'sharedTest'. Unfortunately, UiScreen<S> is not support in
        shared tests. We suggest that you either:
        1. Use KScreen<S> instead. This option supports 'sharedTest'
        2. Disable 'sharedTest' by inheriting from TestCase(Kaspresso.Builder.simple(sharedTest = false)) instead.

    """.trimIndent()
)
