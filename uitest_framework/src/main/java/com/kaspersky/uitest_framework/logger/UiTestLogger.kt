package com.kaspersky.uitest_framework.logger

interface UiTestLogger {

    fun i(text: String)

    fun d(text: String)

    fun e(text: String)

    fun i(tag: String, text: String)

    fun d(tag: String, text: String)

    fun e(tag: String, text: String)
}