package com.kaspersky.kaspresso.logger.composite

import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The logger with caching for logging composite messages with 3 levels: info, debug and error.
 */
class CompositeLogger(logger: UiTestLogger) {

    private val compositeInfo: CompositeInfo by lazy { CompositeInfo(logger) }
    private val compositeDebug: CompositeDebug by lazy { CompositeDebug(logger) }
    private val compositeError: CompositeError by lazy { CompositeError(logger) }

    fun composeInfo(text: String): CompositeInfo = compositeInfo.apply { composeInfo(text) }
    fun composeDebug(text: String): CompositeDebug = compositeDebug.apply { composeDebug(text) }
    fun composeError(text: String): CompositeError = compositeError.apply { composeError(text) }
}