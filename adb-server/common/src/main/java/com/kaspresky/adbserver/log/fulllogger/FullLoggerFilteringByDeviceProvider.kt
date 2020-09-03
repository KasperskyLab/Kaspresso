package com.kaspresky.adbserver.log.fulllogger

import com.kaspresky.adbserver.log.filterlog.FullLoggerOptimiser

internal class FullLoggerFilteringByDeviceProvider : FullLogger {

    private val loggersMap: MutableMap<String?, FullLogger> = hashMapOf()
    private var logMode: FullLogger.LogLevel = FullLogger.LogLevel.INFO
    private var desktopName = "Default"

    override fun log(
        logLevel: FullLogger.LogLevel?,
        deviceName: String?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        getFullLogger(deviceName).log(logLevel, deviceName, tag, method, text)
    }

    fun setRunMode(logMode: FullLogger.LogLevel) {
        this.logMode = logMode
    }

    fun setDesktopName(desktop: String) {
        desktopName = desktop
    }

    private fun getFullLogger(deviceName: String?): FullLogger {
        if (loggersMap.containsKey(deviceName)) {
            return loggersMap[deviceName] ?: throw RuntimeException("It's unbelievable!")
        }
        val fullLogger = FullLoggerSystemImpl(logMode, desktopName)
        loggersMap[deviceName] = fullLogger
        return fullLogger
    }
}
