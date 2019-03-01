package com.kaspersky.uitest_framework.logger

import android.util.Log

class UiTestPrinter : Printer {

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