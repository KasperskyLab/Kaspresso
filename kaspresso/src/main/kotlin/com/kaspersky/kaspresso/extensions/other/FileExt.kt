package com.kaspersky.kaspresso.extensions.other

import com.kaspersky.kaspresso.configurator.Configurator
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 *  Writes given text to file catching IOExceptions and logging it.
 *
 *  @param data text to write.
 */
fun File.safeWrite(data: String) {
    val log = Configurator.logger
    try {
        this.writeText(data)
    } catch (e: FileNotFoundException) {
        log.e("Can not create file: ${e.message}")
    } catch (e: IOException) {
        log.e(e.getStackTraceAsString())
    }
}
