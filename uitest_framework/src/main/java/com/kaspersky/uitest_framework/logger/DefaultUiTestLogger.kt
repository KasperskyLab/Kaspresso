package com.kaspersky.uitest_framework.logger

import android.util.Log

object DefaultUiTestLogger : UiTestLogger {

    private const val DEFAULT_TAG: String = "UI_TESTING"

    override fun i(text: String) {
        Log.i(DEFAULT_TAG, text)
    }

    override fun d(text: String) {
        Log.d(DEFAULT_TAG, text)
    }

    override fun e(text: String) {
        Log.e(DEFAULT_TAG, text)
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