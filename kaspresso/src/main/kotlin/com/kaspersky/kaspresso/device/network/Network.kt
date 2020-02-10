package com.kaspersky.kaspresso.device.network

import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * The interface to work with network settings.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Network {

    /**
     * Enables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    @RequiresAdbServer
    fun enable()

    /**
     * Disables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    @RequiresAdbServer
    fun disable()

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    fun toggleWiFi(enable: Boolean)
}