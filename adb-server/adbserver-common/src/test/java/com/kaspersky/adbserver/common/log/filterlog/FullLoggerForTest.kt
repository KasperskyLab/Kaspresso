package com.kaspersky.adbserver.common.log.filterlog

import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import com.kaspersky.adbserver.common.log.logger.LogLevel

internal class FullLoggerForTest : FullLogger {

    val list: MutableList<String> = mutableListOf()

    override fun log(
        logLevel: LogLevel?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        val entry = "${logLevel ?: ""}${tag ?: ""}${method ?: ""}${text ?: ""}"
        list.add(entry)
    }
}
