package com.kaspersky.kaspresso.device.server

import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.utils.AdbLoggerReflection
import com.kaspersky.kaspresso.logger.UiTestLogger

internal class AdbServerLoggerKaspressoImpl(
    private val showLogLevel: LogLevel,
    private val logger: UiTestLogger
) : Logger {

    companion object {
        private const val TAG = "KASPRESSO_ADBSERVER"
        private const val CLASS = "class="
        private const val METHOD = "method="
        private const val MESSAGE = "message: "
    }

    override fun v(text: String) {
        callLog(logLevel = LogLevel.VERBOSE, originalText = text) { formattedText -> logger.d(TAG, formattedText) }
    }

    override fun d(text: String) {
        callLog(logLevel = LogLevel.DEBUG, originalText = text) { formattedText -> logger.d(TAG, formattedText) }
    }

    override fun i(text: String) {
        callLog(logLevel = LogLevel.INFO, originalText = text) { formattedText -> logger.i(TAG, formattedText) }
    }

    override fun w(text: String) {
        callLog(logLevel = LogLevel.WARN, originalText = text) { formattedText -> logger.w(TAG, formattedText) }
    }

    override fun e(text: String) {
        callLog(logLevel = LogLevel.ERROR, originalText = text) { formattedText -> logger.e(TAG, formattedText) }
    }

    private fun callLog(logLevel: LogLevel, originalText: String, logAction: (String) -> Unit) {
        if (logLevel < showLogLevel) {
            return
        }
        if (logLevel <= LogLevel.DEBUG) {
            val formattedText = "$CLASS${AdbLoggerReflection.getGeneratedClass()} " +
                    "$METHOD${AdbLoggerReflection.getGeneratedMethod()} $MESSAGE$originalText"
            logAction(formattedText)
            return
        }
        logAction(originalText)
    }
}
