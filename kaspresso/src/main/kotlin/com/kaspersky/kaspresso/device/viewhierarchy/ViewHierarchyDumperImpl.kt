package com.kaspersky.kaspresso.device.viewhierarchy

import android.util.Log
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

class ViewHierarchyDumperImpl(
    private val device: UiDevice,
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider
) : ViewHierarchyDumper {

    override fun dump(tag: String): Unit = doDumpAndApply(tag, null)

    override fun dumpAndApply(tag: String, block: File.() -> Unit): Unit = doDumpAndApply(tag, block)

    private fun doDumpAndApply(tag: String, block: (File.() -> Unit)?) {
        try {
            val logcatFile: File = resourceFilesProvider.provideViewHierarchyFile(tag)
            device.dumpWindowHierarchy(logcatFile)
            block?.invoke(logcatFile)
        } catch (e: Throwable) {
            logger.e("View hierarchy dumping error occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
