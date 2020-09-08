package com.kaspersky.adbserver.common.log

import com.kaspersky.adbserver.common.log.filterlog.FullLoggerOptimiser
import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import com.kaspersky.adbserver.common.log.fulllogger.FullLoggerSystemImpl
import com.kaspersky.adbserver.common.log.logger.LogPolicy
import com.kaspersky.adbserver.common.log.logger.DesktopLogger
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.logger.LoggerImpl

/**
 * The singleton to provide Logger interface and to hide an implementation
 */
object LoggerFactory {

    fun getDesktopLogger(logPolicy: LogPolicy, desktopName: String): DesktopLogger {
        val logger = getCommonLogger(logPolicy, desktopName)
        return DesktopLogger(logger, logPolicy, desktopName)
    }

    fun getDesktopLoggerReflectingDevice(desktopLogger: DesktopLogger, deviceName: String): Logger {
        val logPolicy = desktopLogger.logPolicy
        val desktopName = desktopLogger.desktopName
        return getCommonLogger(logPolicy, desktopName, deviceName)
    }

    fun getDeviceLogger(logPolicy: LogPolicy): Logger =
        getCommonLogger(logPolicy)

    private fun getCommonLogger(logPolicy: LogPolicy, desktopName: String? = null, deviceName: String? = null): Logger {
        val logMode: FullLogger.LogLevel =
            if (logPolicy == LogPolicy.INFO_ONLY) FullLogger.LogLevel.INFO else FullLogger.LogLevel.DEBUG
        val fullLogger = FullLoggerSystemImpl(logMode, desktopName, deviceName)
        val fullLoggerWrapper =
            if (logPolicy == LogPolicy.DEBUG_CUT) FullLoggerOptimiser(fullLogger) else fullLogger
        return LoggerImpl(fullLoggerWrapper)
    }
}
