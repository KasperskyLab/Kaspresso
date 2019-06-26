package com.kaspersky.kaspresso.logger.composite

import com.kaspersky.kaspresso.logger.UiTestLogger

class CompositeInfo internal constructor(
    private val logger: UiTestLogger
) {
    private val builder = StringBuilder()

    fun composeInfo(text: String): CompositeInfo {
        builder.append(text)
        return this
    }

    fun logInfo() {
        with(builder) {
            logger.i(toString())
            clear()
        }
    }

    fun logInfo(tag: String) {
        with(builder) {
            logger.i(tag, toString())
            clear()
        }
    }
}