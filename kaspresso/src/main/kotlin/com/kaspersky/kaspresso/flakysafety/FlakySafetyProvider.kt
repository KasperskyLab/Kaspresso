package com.kaspersky.kaspresso.flakysafety

interface FlakySafetyProvider {

    fun <T> flakySafely(failureMessage: String? = null, action: () -> T): T
}

fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(failureMessage: String? = null, action: () -> T): T =
    if (this != null) flakySafely(failureMessage, action) else action.invoke()