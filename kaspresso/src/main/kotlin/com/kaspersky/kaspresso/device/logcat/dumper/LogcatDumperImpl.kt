package com.kaspersky.kaspresso.device.logcat.dumper

import android.util.Log
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.kaspresso.Kaspresso.Companion.DEFAULT_LIB_LOGGER_TAG
import com.kaspersky.kaspresso.kaspresso.Kaspresso.Companion.DEFAULT_TEST_LOGGER_TAG
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

class LogcatDumperImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val logcat: Logcat,
    private val loggerTags: List<String>
) : LogcatDumper {

    override fun dump(tag: String): Unit = doDump(tag, null)

    override fun dumpAndApply(tag: String, block: File.() -> Unit): Unit = doDump(tag, block)

    private fun doDump(tag: String, block: (File.() -> Unit)?) {
        try {
            val logcatFile: File = resourceFilesProvider.provideLogcatFile(tag)
            logcat.dumpLogcat(
                file = logcatFile,
                tags = listOf(DEFAULT_LIB_LOGGER_TAG, DEFAULT_TEST_LOGGER_TAG)
            )
            block?.invoke(logcatFile)
        } catch (e: Throwable) {
            logger.e("Logcat dumping error occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
