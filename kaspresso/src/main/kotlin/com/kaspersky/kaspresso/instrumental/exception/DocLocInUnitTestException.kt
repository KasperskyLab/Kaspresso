package com.kaspersky.kaspresso.instrumental.exception

import java.lang.RuntimeException

class DocLocInUnitTestException : RuntimeException(
    """
        You are executing DocLoc test in JVM environment. It doesn't have any sense because a screenshot capturing in Unit tests is impossible now.
        We suggest only one option: Don't execute this test like a Unit test.
    """.trimIndent()
)
