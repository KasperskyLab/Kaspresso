package com.kaspersky.adbserver.common.log.logger

import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import java.lang.Exception

internal class LoggerImpl(
    private val fullLogger: FullLogger
) : Logger {

    override fun i(text: String) {
        fullLogger.log(
            logLevel = FullLogger.LogLevel.INFO,
            text = text
        )
    }

    override fun d(text: String) {
        fullLogger.log(
            logLevel = FullLogger.LogLevel.DEBUG,
            text = text
        )
    }

    override fun w(text: String) {
        fullLogger.log(
            logLevel = FullLogger.LogLevel.WARNING,
            text = text
        )
    }

    override fun e(exception: Exception) {
        fullLogger.log(
            logLevel = FullLogger.LogLevel.ERROR,
            text = exception.toString()
        )
    }

    override fun e(text: String) {
        fullLogger.log(
            logLevel = FullLogger.LogLevel.ERROR,
            text = text
        )
    }
}
