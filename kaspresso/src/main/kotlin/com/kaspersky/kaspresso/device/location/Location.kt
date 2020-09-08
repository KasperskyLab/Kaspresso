package com.kaspersky.kaspresso.device.location

import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * The interface to work with device's location.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Location {

    /**
     * Enables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun enableGps()

    /**
     * Disables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun disableGps()

    /**
     * Sets current location.
     *
     * Required Permissions: INTERNET
     */
    @RequiresAdbServer
    fun setLocation(lat: Double, lon: Double)
}
