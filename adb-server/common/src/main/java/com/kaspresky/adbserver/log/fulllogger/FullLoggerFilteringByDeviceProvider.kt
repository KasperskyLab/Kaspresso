package com.kaspresky.adbserver.log.fulllogger

import com.kaspresky.adbserver.log.filterlog.FullLoggerOptimiser

internal class FullLoggerFilteringByDeviceProvider : FullLogger {

    private val loggersMap: MutableMap<String?, FullLogger> = hashMapOf()

    override fun log(
        logType: FullLogger.LogType?,
        deviceName: String?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        getFullLogger(deviceName).log(logType, deviceName, tag, method, text)
    }

    private fun getFullLogger(deviceName: String?): FullLogger {
        if (loggersMap.containsKey(deviceName)) {
            return loggersMap[deviceName] ?: throw RuntimeException("It's unbelievable!")
        }
        val fullLogger = FullLoggerOptimiser(
            FullLoggerSystemImpl()
        )
        loggersMap[deviceName] = fullLogger
        return fullLogger
    }
}
