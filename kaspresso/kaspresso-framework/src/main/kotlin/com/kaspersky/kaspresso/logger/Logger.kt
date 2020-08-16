package com.kaspersky.kaspresso.logger

/**
 * The interface for base logging with 3 levels: info, debug and error.
 */
interface Logger {

    /**
     * Info level of logging.
     */
    fun i(text: String)

    /**
     * Debug level of logging.
     */
    fun d(text: String)

    /**
     * Warning level of logging.
     */
    fun w(text: String)

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
     * Warning level of logging.
     */
    fun w(tag: String, text: String)

    /**
     * Error level of logging with tag.
     */
    fun e(tag: String, text: String)
}
