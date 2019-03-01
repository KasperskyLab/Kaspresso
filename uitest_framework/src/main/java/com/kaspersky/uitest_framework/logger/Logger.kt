package com.kaspersky.uitest_framework.logger

object Logger {

    private const val TAG = "UI_TESTING"

    private val printer: Printer by lazy { UiTestPrinter() }

    fun i(tag: String, text: String) = printer.i(tag, text)

    fun d(tag: String, text: String) = printer.d(tag, text)

    fun e(tag: String, text: String) = printer.e(tag, text)

    fun i(text: String) = i(TAG, text)

    fun d(text: String) = d(TAG, text)

    fun e(text: String) = d(TAG, text)
}