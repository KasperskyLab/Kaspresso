package com.kaspersky.kaspresso.flakysafety

interface FlakySafetyProvider {

    fun <T> flakySafely(action: () -> T): T

    fun <T> flakySafely(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: MutableSet<Class<out Throwable>>? = null,
        failureMessage: String? = null,
        action: () -> T
    ): T
}

internal fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(action: () -> T): T =
    if (this != null) flakySafely(action) else action.invoke()