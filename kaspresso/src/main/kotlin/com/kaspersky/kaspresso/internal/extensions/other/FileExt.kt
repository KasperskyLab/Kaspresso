package com.kaspersky.kaspresso.internal.extensions.other

import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 *  Writes given text to file catching IOExceptions and logging it.
 *
 *  @param data text to write.
 */
internal fun File.safeWrite(logger: UiTestLogger, data: String) {
    try {
        this.writeText(data)
    } catch (e: FileNotFoundException) {
        logger.e("Can not create file: ${e.message}")
    } catch (e: IOException) {
        logger.e(e.getStackTraceAsString())
    }
}

/**
 *  Creates a directory with all needed parent dirs, then grants RWX permissions.
 */
internal fun File.createDirectoryRWX() {
    mkdirs()
    setReadable(true, false)
    setWritable(true, false)
    setExecutable(true, false)
}
