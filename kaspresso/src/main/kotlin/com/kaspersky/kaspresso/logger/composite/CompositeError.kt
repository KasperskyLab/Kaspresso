package com.kaspersky.kaspresso.logger.composite

import com.kaspersky.kaspresso.logger.UiTestLogger
import java.lang.StringBuilder

class CompositeError internal constructor(
    private val logger: UiTestLogger
) {
    private val builder = StringBuilder()

    fun composeError(text: String): CompositeError {
        builder.append(text)
        return this
    }

    fun logError() {
        with(builder) {
            logger.e(toString())
            clear()
        }
    }

    fun logError(tag: String) {
        with(builder) {
            logger.e(tag, toString())
            clear()
        }
    }
}