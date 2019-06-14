package com.kaspersky.kaspresso.logger

/**
 * A class to provide access to external logger outside. Implements [UiTestLogger] interface and delegates all
 * calls to wrapped implementation of [UiTestLogger].
 */
class KLogger(
    private val logger: UiTestLogger,
    override var startText: String? = null
): UiTestLogger {

    override fun i(text: String) = logger.i(text)
    override fun d(text: String) = logger.d(text)
    override fun e(text: String) = logger.e(text)

    override fun i(tag: String, text: String) = logger.i(tag, text)
    override fun d(tag: String, text: String) = logger.d(tag, text)
    override fun e(tag: String, text: String) = logger.e(tag, text)
}