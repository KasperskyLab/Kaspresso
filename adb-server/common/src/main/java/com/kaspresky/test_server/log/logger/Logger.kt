package com.kaspresky.test_server.log.logger

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
     * Info level of logging with tag.
     */
    fun i(method: String, text: String)

    /**
     * Debug level of logging with tag.
     */
    fun d(text: String)

    /**
     * Debug level of logging with tag.
     */
    fun d(method: String, text: String)

    /**
     * Error level of logging with tag.
     */
    fun e(exception: Exception)

    /**
     * Error level of logging with tag.
     */
    fun e(method: String, exception: Exception)
}