package com.kaspersky.kaspresso.internal.extensions.other

import android.util.Log
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Writes given text to file catching IOExceptions and logging it.
 *
 * @param data the text to write.
 */
internal fun File.safeWrite(logger: UiTestLogger, data: String) {
    try {
        this.writeText(data)
    } catch (e: FileNotFoundException) {
        logger.e("Can not create file: ${e.message}")
    } catch (ex: IOException) {
        logger.e("Exception during proxy invocation: $ex, ${Log.getStackTraceString(ex)}")
    }
}

/**
 * Creates a directory with all needed parent dirs, then grants RWX permissions.
 */
internal fun File.createDirectoryRWX() {
    mkdirs()
    setReadable(true, false)
    setWritable(true, false)
    setExecutable(true, false)
}

internal fun File.createIfNeeded(): File = apply {
    if (!exists()) {
        createDirectoryRWX()
    }
}
