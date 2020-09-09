package com.kaspersky.adbserver.common.log.logger

import com.kaspersky.adbserver.common.log.fulllogger.FullLogger

internal class LoggerImpl(
    private val fullLogger: FullLogger
) : Logger {

    override fun v(text: String) {
        fullLogger.log(
            logLevel = LogLevel.VERBOSE,
            text = text
        )
    }

    override fun d(text: String) {
        fullLogger.log(
            logLevel = LogLevel.DEBUG,
            text = text
        )
    }

    override fun i(text: String) {
        fullLogger.log(
            logLevel = LogLevel.INFO,
            text = text
        )
    }

    override fun w(text: String) {
        fullLogger.log(
            logLevel = LogLevel.WARN,
            text = text
        )
    }

    override fun e(text: String) {
        fullLogger.log(
            logLevel = LogLevel.ERROR,
            text = text
        )
    }
}
