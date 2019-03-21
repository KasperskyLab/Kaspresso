package com.kaspersky.kaspresso.device.files

interface FilesManager {

    /**
     *  Performs adb push.
     *
     *  @param serverPath file path relative to the server directory.
     *  @param devicePath path to copy.
     */
    fun push(serverPath: String, devicePath: String)
}