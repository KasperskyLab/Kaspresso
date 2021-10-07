package com.kaspersky.components.kautomator.common

import java.util.Locale

internal const val ROBOLECTRIC_TEST_RUNNER = "org.robolectric.RobolectricTestRunner"

private var cachedEnvironment: Environment? = null

/**
 * Get the [Environment] where the test is executing
 */
fun getEnvironment(): Environment {
    if (cachedEnvironment != null) return cachedEnvironment!!

    val runtimeProperty = System.getProperty("java.runtime.name")
    val environment =
        if (runtimeProperty?.toLowerCase(Locale.ROOT)?.contains("android") == true) Environment.AndroidRuntime
        else if (hasClass(ROBOLECTRIC_TEST_RUNNER)) return Environment.Robolectric
        else Environment.Unknown("Java runtime property: $runtimeProperty. $ROBOLECTRIC_TEST_RUNNER is not found")
    cachedEnvironment = environment
    return environment
}

private fun hasClass(className: String): Boolean {
    return try {
        Class.forName(className) != null
    } catch (e: ClassNotFoundException) {
        false
    }
}

sealed class Environment {
    object AndroidRuntime : Environment()
    object Robolectric : Environment()
    data class Unknown(val additionalInfo: String) : Environment()
}
