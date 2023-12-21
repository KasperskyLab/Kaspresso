package com.kaspersky.kaspresso.plugin

import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import com.kaspersky.adbserver.common.log.logger.LogLevel
import org.gradle.api.logging.Logger
import org.gradle.api.logging.LogLevel as GradleLogLevel

class GradleFullLogger(private val logger: Logger) : FullLogger {
    override fun log(logLevel: LogLevel?, tag: String?, method: String?, text: String?) {
        logger.log(logLevel.toGradleLogLevel(), "[$tag][$method]$text")
    }
}

private fun LogLevel?.toGradleLogLevel(): GradleLogLevel = when (this) {
    null -> GradleLogLevel.LIFECYCLE
    LogLevel.VERBOSE -> GradleLogLevel.LIFECYCLE
    LogLevel.DEBUG -> GradleLogLevel.DEBUG
    LogLevel.INFO -> GradleLogLevel.INFO
    LogLevel.WARN -> GradleLogLevel.WARN
    LogLevel.ERROR -> GradleLogLevel.ERROR
}
