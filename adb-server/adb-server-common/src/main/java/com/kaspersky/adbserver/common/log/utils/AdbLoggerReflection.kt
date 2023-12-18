package com.kaspersky.adbserver.common.log.utils

object AdbLoggerReflection {

    private const val IGNORE = "log"

    fun getGeneratedClass(): String =
        getStackElement().className.substringAfterLast('.')

    fun getGeneratedMethod(): String =
        getStackElement().methodName

    private fun getStackElement(): StackTraceElement =
        Throwable().stackTrace
            .first { !it.className.contains(IGNORE, ignoreCase = true) }
}
