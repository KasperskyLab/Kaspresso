package com.kaspersky.kaspresso.flakysafety

data class FlakySafetyParams(
    var timeoutMs: Long,
    var intervalMs: Long,
    var allowedExceptions: Set<Class<out Throwable>>
)