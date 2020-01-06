package com.kaspersky.kaspresso.device.files

/**
 * The interface to work with file permissions.
 */
interface Files {

    /**
     * Performs adb push.
     *
     * Required Permissions: INTERNET.
     *
     * @param serverPath a file path relative to the server directory.
     * @param devicePath a path to copy.
     */
    fun push(serverPath: String, devicePath: String)

    /**
     * Removes a file by given path.
     *
     * Required Permissions: INTERNET
     *
     * @param path a path to remove
     */
    fun remove(path: String)

    /**
     * Performs adb pull.
     *
     * Required Permissions: INTERNET.
     *
     * @param devicePath a file path relative to the device directory.
     * @param serverPath a path to copy. (If empty - pulls in adbServer directory)
     */
    fun pull(devicePath: String, serverPath: String = "")
}