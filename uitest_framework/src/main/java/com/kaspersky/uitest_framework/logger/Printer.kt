package com.kaspersky.uitest_framework.logger

interface Printer {

    fun i(tag: String, text: String)

    fun d(tag: String, text: String)

    fun e(tag: String, text: String)
}