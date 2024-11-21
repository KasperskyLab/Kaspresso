package com.kaspersky.kaspresso.device.bluetooth

/**
 * The interface to work with bluetooth settings.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Bluetooth {

    /**
     * Enables Bluetooth on the device using adb.
     */
    fun enable()

    /**
     * Disables Bluetooth on the device using adb.
     */
    fun disable()
}
