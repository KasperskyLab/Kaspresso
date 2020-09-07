package com.kaspersky.adbserver.common.log.logger

import java.lang.Exception

/**
 * Common interface to log all actions
 */
interface Logger {

    /**
     * Info level of logging with tag.
     */
    fun i(text: String)

    /**
     * Debug level of logging with tag.
     */
    fun d(text: String)

    /**
     * Warning level of logging.
     */
    fun w(text: String)

    /**
     * Error level of logging with tag.
     */
    fun e(exception: Exception)

    /**
     * Error level of logging with tag.
     */
    fun e(text: String)
}
