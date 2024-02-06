package com.kaspersky.adbserver.common.log

import com.kaspersky.adbserver.common.log.filterlog.FullLoggerOptimiser
import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import com.kaspersky.adbserver.common.log.fulllogger.FullLoggerSystemImpl
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.logger.LoggerImpl

/**
 * The singleton to provide Logger interface and to hide an implementation
 */
object LoggerFactory {

    fun getDesktopLogger(
        logLevel: LogLevel,
        desktopName: String,
        fullLogger: FullLogger = FullLoggerSystemImpl(logLevel, desktopName, null)
    ): DesktopLogger {
        val logger = getCommonLogger(logLevel, desktopName, fullLogger = fullLogger)
        return DesktopLogger(logger, logLevel, desktopName)
    }

    fun getDesktopLoggerReflectingDevice(desktopLogger: DesktopLogger, deviceName: String): Logger {
        val logLevel = desktopLogger.logLevel
        val desktopName = desktopLogger.desktopName
        return getCommonLogger(logLevel, desktopName, deviceName)
    }

    fun getDeviceLogger(logLevel: LogLevel): Logger =
        getCommonLogger(logLevel)

    private fun getCommonLogger(
        logLevel: LogLevel,
        desktopName: String? = null,
        deviceName: String? = null,
        fullLogger: FullLogger = FullLoggerSystemImpl(logLevel, desktopName, deviceName)
    ): Logger {
        val fullLoggerWrapper =
            if (logLevel == LogLevel.DEBUG) FullLoggerOptimiser(originalFullLogger = fullLogger, generateLogs = true) else fullLogger
        return LoggerImpl(fullLoggerWrapper)
    }
}
