package com.kaspresky.adbserver.log.fulllogger

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Presents logs in the form like:
 * INFO 17/08/2020 09:53:52 desktop=Desktop-5037 device=emulator-5554 message:updated state=CONNECTED
 */
private const val EMPTY_STRING = ""
internal class FullLoggerSystemImpl(
    private val logMode: FullLogger.LogLevel,
    private val desktopName: String?
) : FullLogger {
    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    companion object {
        private const val DEVICE = "device="
        private const val TAG = "tag="
        private const val METHOD = "method="
        private const val MESSAGE = "message:"
        private const val DESKTOP = "desktop="
    }

    override fun log(
        logLevel: FullLogger.LogLevel?,
        deviceName: String?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        if (logLevel != null && logMode <= logLevel) {
            val fullLog = "${getLogType(logLevel)} ${getDate()} ${getDesktop()} ${getDevice(deviceName)} ${getTag(tag)} " +
                    "${getMethod(method)} ${getText(text)}"
            if (logLevel == FullLogger.LogLevel.ERROR) {
                System.err.println(fullLog)
                return
            }
            println(fullLog)
        }
    }

    private fun getLogType(logLevel: FullLogger.LogLevel?): String =
            logLevel?.name ?: EMPTY_STRING

    private fun getDevice(deviceName: String?): String =
            if (deviceName != null) {
                "$DEVICE$deviceName"
            } else {
                EMPTY_STRING
            }

    private fun getDesktop(): String =
            if (desktopName != null) {
                "$DESKTOP$desktopName"
            } else {
                EMPTY_STRING
            }

    private fun getTag(tag: String?): String =
            if (logMode == FullLogger.LogLevel.DEBUG && tag != null) {
                "$TAG$tag "
            } else EMPTY_STRING

    private fun getMethod(method: String?): String =
            if (logMode == FullLogger.LogLevel.DEBUG && method != null) {
                "$METHOD$method "
            } else EMPTY_STRING

    private fun getText(text: String?): String =
            if (text != null) {
                "$MESSAGE$text"
            } else EMPTY_STRING

    private fun getDate(): String {
        val date = formatter.format(Date())
        return if (date != null) {
            "$date "
        } else {
            EMPTY_STRING
        }
    }
}
