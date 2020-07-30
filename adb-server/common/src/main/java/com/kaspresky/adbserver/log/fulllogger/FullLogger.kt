package com.kaspresky.adbserver.log.fulllogger

internal interface FullLogger {

    fun log(
        logLevel: LogLevel? = null,
        deviceName: String? = null,
        tag: String? = null,
        method: String? = null,
        text: String? = null
    )

    enum class LogLevel {
        DEBUG,
        INFO,
        ERROR
    }
}
