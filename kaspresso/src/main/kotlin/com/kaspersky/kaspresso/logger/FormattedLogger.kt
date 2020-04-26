package com.kaspersky.kaspresso.logger

/**
 * The interface for formatted logging.
 */
interface FormattedLogger {

    /**
     * Draws up the text as a section block.
     */
    fun section(text: String)

    /**
     * Draws up the text as a header block.
     */
    fun header(text: String)

    /**
     * Draws up the text as a header block.
     */
    fun footer(text: String)

    /**
     * Draws a line.
     */
    fun line()
}
