package com.kaspersky.components.kautomator.common

import java.util.Locale

private const val ROBOLECTRIC_TEST_RUNNER = "org.robolectric.RobolectricTestRunner"
private const val JAVA_RUNTIME_PROPERTY = "java.runtime.name"
private const val ANDROID_RUNTIME = "android"

/**
 * Get the [Environment] where the test is executing
 */
val environment: Environment by lazy {
    val runtimeProperty = System.getProperty(JAVA_RUNTIME_PROPERTY)
    when {
        runtimeProperty?.toLowerCase(Locale.ROOT)?.contains(ANDROID_RUNTIME) == true -> Environment.AndroidRuntime
        hasClass(ROBOLECTRIC_TEST_RUNNER) -> Environment.Robolectric
        else -> throw RuntimeException(
            """
                This environment is not supported by Kaspresso.
                Current environment: [Java runtime property: $runtimeProperty. $ROBOLECTRIC_TEST_RUNNER is not found].
                Please let us know by creating an issue if you desire to support a new environment that is differ from Android Runtime or JVM with Robolectric support.
            """.trimIndent()
        )
    }
}

private fun hasClass(className: String): Boolean {
    return try {
        Class.forName(className)
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}

sealed class Environment {
    object AndroidRuntime : Environment()
    object Robolectric : Environment()
}
