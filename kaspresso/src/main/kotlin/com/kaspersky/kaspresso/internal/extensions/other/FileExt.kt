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
    } catch (e: IOException) {
        logger.e(Log.getStackTraceString(e))
    }
}

/**
 * Creates a file if it does not exist with all needed parent dirs, then grants RWX permissions.
 */
fun File.createFileIfNeeded(): File = apply {
    if (!exists()) {
        createNewFile()
        grantRwxPermissions()
    }
}

/**
 * Creates a directory if it does not exist with all needed parent dirs, then grants RWX permissions.
 */
fun File.createDirIfNeeded(): File = apply {
    if (!exists()) {
        mkdirs()
        grantRwxPermissions()
    }
}

internal fun File.grantRwxPermissions(): File = apply {
    setReadable(true, false)
    setWritable(true, false)
    setExecutable(true, false)
}
