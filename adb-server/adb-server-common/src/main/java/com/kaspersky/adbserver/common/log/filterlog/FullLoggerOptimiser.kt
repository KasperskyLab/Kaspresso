package com.kaspersky.adbserver.common.log.filterlog

import com.kaspersky.adbserver.common.log.fulllogger.FullLogger
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.utils.AdbLoggerReflection
import java.util.ArrayDeque
import java.util.Deque

internal class FullLoggerOptimiser(
    private val originalFullLogger: FullLogger,
    private val generateLogs: Boolean,
    recordingStackMaxSize: Int = DEFAULT_RECORDING_STACK_MAX_SIZE
) : FullLogger {

    private companion object {
        private const val SLASH_AT_THE_BEGINNING: Int = 40
        private const val SLASH_AT_THE_END: Int = 100
        private const val DEFAULT_RECORDING_STACK_MAX_SIZE: Int = 100
    }

    private val logStack: Deque<LogData> = ArrayDeque()
    private var logRecorder: LogRecorder = LogRecorder(recordingStackMaxSize)

    override fun log(
        logLevel: LogLevel?,
        tag: String?,
        method: String?,
        text: String?
    ) {
        val formattedTag = tag ?: if (generateLogs) AdbLoggerReflection.getGeneratedClass() else null
        val formattedMethod = method ?: if (generateLogs) AdbLoggerReflection.getGeneratedMethod() else null
        handleLog(
            key = "$logLevel$tag$method$text",
            action = { originalFullLogger.log(logLevel, formattedTag, formattedMethod, text) },
            logLevel = logLevel
        )
    }

    private fun handleLog(key: String, logLevel: LogLevel?, action: () -> Unit) {
        val logData = LogData(key, action)
        val position = logStack.indexOf(logData)
        val answer = logRecorder.put(position, LogData(key, action))
        when (answer) {
            is RecordInProgress -> { return }
            is ReadyRecord -> {
                outputRecord(answer, logLevel)
                updateState(answer)
            }
        }
    }

    private fun outputRecord(readyRecord: ReadyRecord, logLevel: LogLevel?) {
        // prepare the first and the last log for recorded Fragment if it's needed
        var fragmentStartString: String? = null
        var fragmentEndString: String? = null
        if (readyRecord.countOfRecordingStack > 0) {
            fragmentStartString = "/".repeat(SLASH_AT_THE_BEGINNING) +
                    "FRAGMENT IS REPEATED ${readyRecord.countOfRecordingStack} TIMES" +
                    "/".repeat(SLASH_AT_THE_BEGINNING)
            fragmentEndString = "/".repeat(SLASH_AT_THE_END)
        }
        // output record
        fragmentStartString?.let { originalFullLogger.log(logLevel = logLevel, tag = "ServiceInfo", method = "Start", text = fragmentStartString) }
        readyRecord.recordingStack.descendingIterator().forEach { it.logOutput.invoke() }
        fragmentEndString?.let { originalFullLogger.log(logLevel = logLevel, tag = "ServiceInfo", method = "End", text = fragmentEndString) }
        // output remained part
        readyRecord.remainedStack.descendingIterator().forEach { it.logOutput.invoke() }
    }

    private fun updateState(readyRecord: ReadyRecord) {
        if (readyRecord.recordingStack.isNotEmpty()) {
            logStack.clear()
        }
        readyRecord.remainedStack.descendingIterator().forEach {
            logStack.addFirst(it)
        }
    }
}
