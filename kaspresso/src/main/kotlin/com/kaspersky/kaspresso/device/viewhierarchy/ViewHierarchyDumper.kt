package com.kaspersky.kaspresso.device.viewhierarchy

import java.io.File

interface ViewHierarchyDumper {

    /**
     * Dumps view hierarchy with specific tag.
     */
    fun dump(tag: String)

    /**
     * Dumps view hierarchy with specific tag and applies a function to the result file.
     */
    fun dumpAndApply(tag: String, block: File.() -> Unit)
}
