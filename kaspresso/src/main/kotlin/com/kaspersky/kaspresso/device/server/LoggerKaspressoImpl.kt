package com.kaspersky.kaspresso.device.server

import com.kaspersky.adbserver.common.log.logger.Logger
import com.kaspersky.adbserver.common.log.utils.AdbLoggerReflection
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.Exception

internal class LoggerKaspressoImpl(
    private val adbServerLogsType: AdbServerLogsType,
    private val logger: UiTestLogger
) : Logger {

    companion object {
        private const val TAG = "KASPRESSO_ADBSERVER"
        private const val CLASS = "class="
        private const val METHOD = "method="
        private const val MESSAGE = "message: "
    }

    override fun i(text: String) {
        callLog(isDebug = false, originalText = text) { formattedText -> logger.i(TAG, formattedText) }
    }

    override fun d(text: String) {
        callLog(isDebug = true, originalText = text) { formattedText -> logger.d(TAG, formattedText) }
    }

    override fun w(text: String) {
        callLog(isDebug = false, originalText = text) { formattedText -> logger.w(TAG, formattedText) }
    }

    override fun e(exception: Exception) {
        callLog(isDebug = false, originalText = exception.message ?: exception.toString()) {
                formattedText -> logger.e(TAG, formattedText)
        }
    }

    override fun e(text: String) {
        callLog(isDebug = false, originalText = text) { formattedText -> logger.e(TAG, formattedText) }
    }

    private fun callLog(isDebug: Boolean, originalText: String, logAction: (String) -> Unit) {
        when (adbServerLogsType) {
            AdbServerLogsType.NO_LOGS -> {}
            AdbServerLogsType.INFO_ONLY -> {
                if (!isDebug) logAction(originalText)
            }
            AdbServerLogsType.DEBUG_FULL -> {
                val formattedText = "$CLASS${AdbLoggerReflection.getGeneratedClass()} " +
                        "$METHOD${AdbLoggerReflection.getGeneratedMethod()} $MESSAGE$originalText"
                logAction(formattedText)
            }
        }
    }
}
