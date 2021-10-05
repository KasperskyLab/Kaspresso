package com.kaspersky.components.kautomator.common

import androidx.test.platform.app.InstrumentationRegistry

/**
 * @return true if an environment is instrumental, or false if the environment is JVM (with Robolectric)
 */
internal fun checkInstrumentalStateOfEnvironment(): Boolean =
    try {
        InstrumentationRegistry.getInstrumentation().uiAutomation != null
    } catch(exception: Exception) {
        false
    }
