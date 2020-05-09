package com.kaspresky.testserver.log.fulllogger

/**
 * Presents logs in the form like:
 * INFO:_____tag=ConnectionMaker____________method=connect()_________________message => start
 * INFO:_____tag=ConnectionMaker____________method=connect()_________________message => current state=DISCONNECTED
 */
internal class FullLoggerSystemImpl : FullLogger {

    companion object {
        private const val COMMON_FIELD_LENGTH = 43
        private const val TAG_FIELD_LENGTH = 5
        private const val DEVICE = "device="
        private const val TAG = "tag="
        private const val METHOD = "method="
        private const val MESSAGE = "message => "
    }

    override fun log(
        logType: FullLogger.LogType?,
        deviceName: String?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        val fullLog = "${getLogType(logType)}${getDevice(deviceName)}${getTag(tag)}" +
                "${getMethod(method)}${getText(text)}"
        if (logType == FullLogger.LogType.ERROR) {
            System.err.println(fullLog)
            return
        }
        println(fullLog)
    }

    private fun getLogType(logType: FullLogger.LogType?): String =
        if (logType != null) { "${getFieldString(logType.name, TAG_FIELD_LENGTH)}____" } else { "" }

    private fun getDevice(deviceName: String?): String =
        if (deviceName != null) { "$DEVICE${deviceName}____" } else { "" }

    private fun getTag(tag: String?): String =
        if (tag != null) { "$TAG${getFieldString(tag)}" } else ""

    private fun getMethod(method: String?): String =
        if (method != null) { "$METHOD${getFieldString(method)}" } else ""

    private fun getText(text: String?): String =
        if (text != null) { "$MESSAGE$text" } else ""

    private fun getFieldString(text: String, length: Int = COMMON_FIELD_LENGTH): String {
        if (text.length >= length) {
            return text + "_"
        }
        return text + "_".repeat(length - text.length)
    }
}
