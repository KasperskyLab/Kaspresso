package com.kaspersky.kaspresso.device.viewhierarchy

import android.util.Log
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

class ViewHierarchyDumperImpl(
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider
) : ViewHierarchyDumper {

    private val device: UiDevice
        get() = instrumentalDependencyProvider.uiDevice

    override fun dump(tag: String): Unit = doDumpAndApply(tag, null)

    override fun dumpAndApply(tag: String, block: File.() -> Unit): Unit = doDumpAndApply(tag, block)

    private fun doDumpAndApply(tag: String, block: (File.() -> Unit)?) {
        try {
            val viewHierarchyFile: File = resourceFilesProvider.provideViewHierarchyFile(tag)
            device.dumpWindowHierarchy(viewHierarchyFile)
            block?.invoke(viewHierarchyFile)
            logger.i("View hierarchy dumped to $viewHierarchyFile")
        } catch (e: Throwable) {
            logger.e("View hierarchy dumping error occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
