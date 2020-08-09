package com.kaspersky.kaspresso.logger

import android.util.Log

/**
 * The default implementation of [UiTestLogger] using [android.util.Log].
 */
class UiTestLoggerImpl(
    private val tag: String
) : UiTestLogger {

    override fun i(text: String) {
        Log.i(tag, text)
    }

    override fun d(text: String) {
        Log.d(tag, text)
    }

    override fun w(text: String) {
        Log.w(tag, text)
    }

    override fun e(text: String) {
        Log.e(tag, text)
    }

    override fun i(tag: String, text: String) {
        Log.i(tag, text)
    }

    override fun d(tag: String, text: String) {
        Log.d(tag, text)
    }

    override fun w(tag: String, text: String) {
        Log.w(tag, text)
    }

    override fun e(tag: String, text: String) {
        Log.e(tag, text)
    }

    /**
     * Draws up info [i] as section block.
     */
    override fun section(text: String) {
        i("---------------------------------------------------------------------------")
        i(text)
        i("---------------------------------------------------------------------------")
    }

    /**
     * Draws up info [i] as header block.
     */
    override fun header(text: String) {
        line()
        i(text)
    }

    /**
     * Draws up info [i] as header block.
     */
    override fun footer(text: String) {
        i(text)
        line()
    }

    /**
     * Draws line info.
     */
    override fun line() {
        i("___________________________________________________________________________")
    }
}
