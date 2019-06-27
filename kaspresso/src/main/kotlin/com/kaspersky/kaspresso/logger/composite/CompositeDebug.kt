package com.kaspersky.kaspresso.logger.composite

import com.kaspersky.kaspresso.logger.UiTestLogger

class CompositeDebug internal constructor(
    private val logger: UiTestLogger
) {
    private val builder = StringBuilder()

    fun composeDebug(text: String): CompositeDebug {
        builder.append(text)
        return this
    }

    fun logDebug() {
        with(builder) {
            logger.d(toString())
            clear()
        }
    }

    fun logDebug(tag: String) {
        with(builder) {
            logger.d(tag, toString())
            clear()
        }
    }
}