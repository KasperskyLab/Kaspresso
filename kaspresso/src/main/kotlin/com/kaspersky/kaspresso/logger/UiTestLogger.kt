package com.kaspersky.kaspresso.logger

/**
 * Base interface for all loggers used in Kaspresso.
 */
interface UiTestLogger {

    /**
     * Info level of logging.
     */
    fun i(text: String)

    /**
     * Debug level of logging.
     */
    fun d(text: String)

    /**
     * Error level of logging.
     */
    fun e(text: String)

    /**
     * Info level of logging with tag.
     */
    fun i(tag: String, text: String)

    /**
     * Debug level of logging with tag.
     */
    fun d(tag: String, text: String)

    /**
     * Error level of logging with tag.
     */
    fun e(tag: String, text: String)
}