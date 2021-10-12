package com.kaspersky.components.kautomator.common

import java.lang.RuntimeException

class KautomatorInUnitTestException internal constructor() : RuntimeException(
    """

        You are using Kautomator classes in Unit tests (with Robolectric support on the JVM environment). Unfortunately, it's impossible in JVM environment.
        We suggest that you either:
        1. Rewrite your test with only Kakao/Espresso and related classes.
        2. Don't execute this test like a Unit test.

    """.trimIndent()
)
