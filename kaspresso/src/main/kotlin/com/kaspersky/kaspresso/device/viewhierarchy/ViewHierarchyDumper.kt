package com.kaspersky.kaspresso.device.viewhierarchy

import java.io.File

interface ViewHierarchyDumper {
    fun dump(tag: String)
    fun dumpAndApply(tag: String, block: File.() -> Unit)
}
