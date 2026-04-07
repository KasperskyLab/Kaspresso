package com.kaspersky.components.kautomator.common

import java.util.Locale

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

private const val ROBOLECTRIC_TEST_RUNNER = "org.robolectric.RobolectricTestRunner"
private const val JAVA_RUNTIME_PROPERTY = "java.runtime.name"
private const val ANDROID_RUNTIME = "android"

/**
 * Get the [Environment] where the test is executing
 */
val environment: Environment by lazy {
    val runtimeProperty = System.getProperty(JAVA_RUNTIME_PROPERTY)
    when {
        runtimeProperty?.lowercase(Locale.ROOT)?.contains(ANDROID_RUNTIME) == true -> Environment.AndroidRuntime
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
