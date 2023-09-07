package com.kaspersky.adbserver.common.log.filterlog

internal class LogData(
    val logMessage: String,
    val logOutput: () -> Unit
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (this === other) {
            return true
        }
        if (other is LogData) {
            return this.logMessage == other.logMessage
        }
        return false
    }

    override fun hashCode(): Int {
        return logMessage.hashCode()
    }
}
