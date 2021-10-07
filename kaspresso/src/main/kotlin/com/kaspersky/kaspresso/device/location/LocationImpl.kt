package com.kaspersky.kaspresso.device.location

import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Location] interface.
 */
class LocationImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer
) : Location {

    /**
     * Enables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    override fun enableGps() {
        setLocationProviders("+gps")
        logger.i("GPS enabled")
    }

    /**
     * Disables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    override fun disableGps() {
        setLocationProviders("-gps")
        logger.i("GPS disabled")
    }

    /**
     * Sets current location.
     *
     * Required Permissions: INTERNET
     */
    override fun setLocation(lat: Double, lon: Double) {
        adbServer.performAdb("emu geo fix $lon $lat") // geo fix uses Lon-Lat, almost everyone uses Lat-Lon
        logger.i("Location set to $lat,$lon")
    }

    private fun setLocationProviders(providers: String) {
        adbServer.performShell("settings put secure location_providers_allowed $providers")
    }
}
