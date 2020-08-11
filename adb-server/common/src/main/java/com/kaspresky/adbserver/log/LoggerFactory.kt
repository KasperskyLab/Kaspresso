package com.kaspresky.adbserver.log

import com.kaspresky.adbserver.log.fulllogger.FullLogger
import com.kaspresky.adbserver.log.fulllogger.FullLoggerFilteringByDeviceProvider
import com.kaspresky.adbserver.log.logger.Logger
import com.kaspresky.adbserver.log.logger.LoggerImpl

/**
 * The singleton to provide Logger interface and to hide an implementation
 */
object LoggerFactory {

    private val fullLogger = FullLoggerFilteringByDeviceProvider()
    private const val DEFAULT_ADB_PORT = "5037"

    fun setRunMode(runMode: String?) {
        if (runMode.equals("debug", true)) {
            fullLogger.setRunMode(FullLogger.LogLevel.DEBUG)
        }
    }

    fun setDesktopName(desktopName: String?) {
        if (!desktopName.isNullOrEmpty()) {
            fullLogger.setDesktopName(desktopName)
        } else {
            fullLogger.setDesktopName(DEFAULT_ADB_PORT)
        }
    }

    fun getLogger(tag: String, deviceName: String? = null): Logger =
        LoggerImpl(tag, deviceName,
            fullLogger
        )
}
