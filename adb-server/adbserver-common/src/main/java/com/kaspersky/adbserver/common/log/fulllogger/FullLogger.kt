package com.kaspersky.adbserver.common.log.fulllogger

internal interface FullLogger {

    fun log(
        logLevel: LogLevel? = null,
        tag: String? = null,
        method: String? = null,
        text: String? = null
    )

    enum class LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}
