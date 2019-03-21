package com.kaspersky.kaspresso.device.files

/**
 * An interface to work with file system.
 */
interface FilesManager {

    /**
     *  Performs adb push.
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    fun push(serverPath: String, devicePath: String)
}