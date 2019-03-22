package com.kaspersky.kaspresso.device.files

/**
 * An interface to work with file permissions.
 */
interface Files {

    /**
     *  Performs adb push.
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    fun push(serverPath: String, devicePath: String)
}