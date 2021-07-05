package com.kaspersky.kaspresso.device.logcat.dumper

import java.io.File

interface LogcatDumper {
    fun dump(tag: String)
    fun dumpAndApply(tag: String, block: File.() -> Unit)
}
