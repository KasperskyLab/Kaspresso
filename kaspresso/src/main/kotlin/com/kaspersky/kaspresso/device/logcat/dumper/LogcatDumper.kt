package com.kaspersky.kaspresso.device.logcat.dumper

import java.io.File

interface LogcatDumper {

    /**
     * Prepares for future logcat dump to file.
     */
    fun charge()

    /**
     * Dumps logcat output with specific tag.
     */
    fun dump(tag: String)

    /**
     * Dumps logcat output with specific tag and applies a function on the dump file.
     */
    fun dumpAndApply(tag: String, block: File.() -> Unit)
}
