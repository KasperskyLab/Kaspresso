package com.kaspersky.uitest_framework.util

import java.io.PrintWriter
import java.io.StringWriter

fun Throwable.getStackTraceAsString(): String {
    val sw = StringWriter()
    val pw = PrintWriter(sw)

    printStackTrace(pw)

    return sw.toString()
}