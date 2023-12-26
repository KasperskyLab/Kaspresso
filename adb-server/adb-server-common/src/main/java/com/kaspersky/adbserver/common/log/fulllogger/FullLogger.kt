package com.kaspersky.adbserver.common.log.fulllogger

import com.kaspersky.adbserver.common.log.logger.LogLevel

internal interface FullLogger {

    fun log(
        logLevel: LogLevel? = null,
        tag: String? = null,
        method: String? = null,
        text: String? = null
    )
}
