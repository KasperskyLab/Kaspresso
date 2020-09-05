package com.kaspresky.adbserver.log.fulllogger

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Presents logs in the form like:
 * INFO 17/08/2020 09:53:52.234 desktop=Desktop-48633 device=emulator-5554 message: updated state=CONNECTED.
 * There are two kinds of logs:
 * 1. INFO 17/08/2020 09:53:52.234 desktop=Desktop-48633 device=emulator-5554 message: updated state=CONNECTED
 * This form is using when logMode (the field) == INFO. Also, a user watches only INFO, WARNING and ERROR logs.
 * 2. INFO 17/08/2020 09:53:52.234 desktop=Desktop-48633 device=emulator-5554 tag=ConnectionMaker method=connect message: updated state=CONNECTED
 * This form is using when logMode (the field) == DEBUG. Also, a user watches all logs.
 */
internal class FullLoggerSystemImpl(
    private val logMode: FullLogger.LogLevel,
    private val desktopName: String?,
    private val deviceName: String?
) : FullLogger {

    companion object {
        private const val DEVICE = "device="
        private const val TAG = "tag="
        private const val METHOD = "method="
        private const val MESSAGE = "message: "
        private const val DESKTOP = "desktop="
        private const val EMPTY_STRING = ""
        private const val IGNORE_CLASSES = "com.kaspresky.adbserver.log"
    }

    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")

    override fun log(
        logLevel: FullLogger.LogLevel?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        if (logLevel != null && logMode <= logLevel) {
            val fullLog = "${getLogType(logLevel)} ${getDate()} ${getDesktop()} ${getDevice()} ${getTag(tag)} " +
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

    private fun getDevice(): String =
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

    private fun getTag(tag: String?): String {
        if (logMode != FullLogger.LogLevel.DEBUG) return EMPTY_STRING
        if (tag != null) return "$TAG$tag "
        val generatedTagName = getStackElement().className.substringAfterLast('.')
        return "$TAG$generatedTagName "
    }

    private fun getMethod(method: String?): String {
        if (logMode != FullLogger.LogLevel.DEBUG) return EMPTY_STRING
        if (method != null) return "$METHOD$method "
        val generatedMethodName = getStackElement().methodName
        return "$METHOD$generatedMethodName "
    }

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

    private fun getStackElement(): StackTraceElement =
        Throwable().stackTrace
            .first { !it.className.contains(IGNORE_CLASSES) }
}
