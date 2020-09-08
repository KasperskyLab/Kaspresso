package com.kaspresky.adbserver.log.filterlog

import com.kaspresky.adbserver.log.fulllogger.FullLogger

internal class FullLoggerForTest : FullLogger {

    val list: MutableList<String> = mutableListOf()

    override fun log(
        logLevel: FullLogger.LogLevel?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        val entry = "${logLevel ?: ""}${tag ?: ""}${method ?: ""}${text ?: ""}"
        list.add(entry)
    }
}