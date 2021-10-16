package com.kaspersky.kaspresso.instrumental.exception

import java.lang.RuntimeException

class DocLocInUnitTestException internal constructor() : RuntimeException(
    """

        You are executing DocLoc test on the JVM environment. It doesn't have any sense because a screenshot capturing in Unit tests is impossible now.
        We suggest only one option: Don't execute this test like a Unit test.

    """.trimIndent()
)
