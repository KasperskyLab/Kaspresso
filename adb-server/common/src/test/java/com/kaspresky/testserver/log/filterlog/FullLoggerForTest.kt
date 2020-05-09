package com.kaspresky.testserver.log.filterlog

import com.kaspresky.testserver.log.fulllogger.FullLogger

internal class FullLoggerForTest : FullLogger {

    val list: MutableList<String> = mutableListOf()

    override fun log(
        logType: FullLogger.LogType?,
        deviceName: String?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        val entry = "${logType ?: ""}${deviceName ?: ""}${tag ?: ""}${method ?: ""}${text ?: ""}"
        list.add(entry)
    }
}