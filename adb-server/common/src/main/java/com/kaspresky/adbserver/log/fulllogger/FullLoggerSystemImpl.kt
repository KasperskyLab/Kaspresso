package com.kaspresky.adbserver.log.fulllogger

import java.text.SimpleDateFormat
import java.util.Date

/**
 * Presents logs in the form like:
 * [INFO] 05/07/2020 14:55:05 device=emulator-5554 tag=ConnectionMaker method=connect message => updated state=CONNECTED
 */
internal class FullLoggerSystemImpl(
    private val logMode: FullLogger.LogLevel,
    private val desktopName: String?
) : FullLogger {
    private val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    companion object {
        private const val COMMON_FIELD_LENGTH = 43
        private const val TAG_FIELD_LENGTH = 8
        private const val DEVICE = "device="
        private const val TAG = "tag="
        private const val METHOD = "method="
        private const val MESSAGE = "message => "
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
            val fullLog = "${getLogType(logLevel)}${getDate()}${getDesktop()}${getDevice(deviceName)}${getTag(tag)}" +
                    "${getMethod(method)}${getText(text)}"
            if (logLevel == FullLogger.LogLevel.ERROR) {
                System.err.println(fullLog)
                return
            }
            println(fullLog)
        }
    }

    private fun getLogType(logLevel: FullLogger.LogLevel?): String =
            if (logLevel != null) {
                getFieldString("[${logLevel.name}]", TAG_FIELD_LENGTH)
            } else {
                ""
            }

    private fun getDevice(deviceName: String?): String =
            if (deviceName != null) {
                "$DEVICE$deviceName    "
            } else {
                ""
            }

    private fun getDesktop(): String =
            if (desktopName != null) {
                "$DESKTOP$desktopName    "
            } else {
                ""
            }

    private fun getTag(tag: String?): String =
            if (tag != null) {
                "$TAG${getFieldString(tag)}"
            } else ""

    private fun getMethod(method: String?): String =
            if (method != null) {
                "$METHOD${getFieldString(method)}"
            } else ""

    private fun getText(text: String?): String =
            if (text != null) {
                "$MESSAGE$text"
            } else ""

    private fun getFieldString(text: String, length: Int = COMMON_FIELD_LENGTH): String {
        if (text.length >= length) {
            return "$text "
        }
        return text + " ".repeat(length - text.length)
    }

    private fun getDate(): String {
        val date = formatter.format(Date())
        return if (date != null) {
            "$date "
        } else {
            ""
        }
    }
}
