package com.kaspersky.kaspresso.device.video

import java.io.File

interface Videos {
    fun record(tag: String)
    fun save()
    fun saveAndApply(block: File.() -> Unit)
}
