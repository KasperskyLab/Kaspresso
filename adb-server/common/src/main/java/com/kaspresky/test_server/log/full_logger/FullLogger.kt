package com.kaspresky.test_server.log.full_logger

internal interface FullLogger {

    fun log(
        logType: LogType? = null,
        deviceName: String? = null,
        tag: String? = null,
        method: String? = null,
        text: String? = null
    )

    enum class LogType {
        INFO,
        DEBUG,
        ERROR
    }
}
