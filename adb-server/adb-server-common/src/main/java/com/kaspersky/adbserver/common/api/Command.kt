package com.kaspersky.adbserver.common.api

import java.io.Serializable

/**
 * Command to execute by AdbServer
 */
abstract class Command : Serializable {
    open val body: List<String>

    constructor(body: List<String>) {
        this.body = body
    }

    constructor(body: String) {
        this.body = listOf(body)
    }
}
