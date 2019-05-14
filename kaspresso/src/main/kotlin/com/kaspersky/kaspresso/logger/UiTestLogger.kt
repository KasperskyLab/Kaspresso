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

    /**
     * Draws up info [i] as section block.
     */
    fun section(text: String) {
        i("---------------------------------------------------------------------------")
        i(text)
        i("---------------------------------------------------------------------------")
    }

    /**
     * Draws up info [i] as header block.
     */
    fun header(text: String) {
        i("___________________________________________________________________________")
        i(text)
    }

    /**
     * Draws up info [i] as header block.
     */
    fun footer(text: String) {
        i(text)
        i("___________________________________________________________________________")
    }
}