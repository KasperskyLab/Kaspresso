package com.kaspresky.adbserver.log.logger

import com.kaspresky.adbserver.log.fulllogger.FullLogger
import java.lang.Exception

internal class LoggerImpl(
    private val tag: String?,
    private val deviceName: String? = null,
    private val fullLogger: FullLogger
) : Logger {

    override fun i(text: String) {
        fullLogger.log(
            logType = FullLogger.LogType.INFO,
            deviceName = deviceName,
            tag = tag,
            text = text
        )
    }

    override fun i(method: String, text: String) {
        fullLogger.log(
            logType = FullLogger.LogType.INFO,
            deviceName = deviceName,
            tag = tag,
            method = method,
            text = text
        )
    }

    override fun d(text: String) {
        fullLogger.log(
            logType = FullLogger.LogType.DEBUG,
            deviceName = deviceName,
            tag = tag,
            text = text
        )
    }

    override fun d(method: String, text: String) {
        fullLogger.log(
            logType = FullLogger.LogType.DEBUG,
            deviceName = deviceName,
            tag = tag,
            method = method,
            text = text
        )
    }

    override fun e(exception: Exception) {
        fullLogger.log(
            logType = FullLogger.LogType.ERROR,
            deviceName = deviceName,
            tag = tag,
            text = exception.toString()
        )
    }

    override fun e(method: String, exception: Exception) {
        fullLogger.log(
            logType = FullLogger.LogType.ERROR,
            deviceName = deviceName,
            tag = tag,
            method = method,
            text = exception.toString()
        )
    }
}
