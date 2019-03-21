package com.kaspersky.kaspresso.logger

import android.util.Log

class UiTestLoggerImpl(
    private val tag: String
) : UiTestLogger {

    override fun i(text: String) {
        Log.i(tag, text)
    }

    override fun d(text: String) {
        Log.d(tag, text)
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

    override fun e(tag: String, text: String) {
        Log.e(tag, text)
    }
}