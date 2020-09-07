package com.kaspersky.kaspresso.device.network

/**
 * The interface to work with network settings.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Network {

    /**
     * Enables wi-fi and mobile data using adb.
     */
    fun enable()

    /**
     * Disables wi-fi and mobile data using adb.
     */
    fun disable()

    /**
     * Toggles only mobile data. Note: it works only if flight mode is off.
     */
    fun toggleMobileData(enable: Boolean)

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    fun toggleWiFi(enable: Boolean)
}
