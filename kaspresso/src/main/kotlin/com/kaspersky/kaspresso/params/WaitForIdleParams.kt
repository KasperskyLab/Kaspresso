package com.kaspersky.kaspresso.params

data class WaitForIdleParams(
    var timeoutMs: Long = DEFAULT_WAIT_FOR_IDLE_TIMEOUT
)

private const val DEFAULT_WAIT_FOR_IDLE_TIMEOUT: Long = 5_000