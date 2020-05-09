package com.kaspresky.testserver.log

import com.kaspresky.testserver.log.fulllogger.FullLogger
import com.kaspresky.testserver.log.fulllogger.FullLoggerFilteringByDeviceProvider
import com.kaspresky.testserver.log.logger.Logger
import com.kaspresky.testserver.log.logger.LoggerImpl

/**
 * The singleton to provide Logger interface and to hide an implementation
 */
object LoggerFactory {

    private val fullLogger: FullLogger =
        FullLoggerFilteringByDeviceProvider()

    fun getLogger(tag: String, deviceName: String? = null): Logger =
        LoggerImpl(tag, deviceName, fullLogger)
}
