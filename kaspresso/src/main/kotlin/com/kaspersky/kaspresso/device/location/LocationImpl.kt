package com.kaspersky.kaspresso.device.location

import android.os.Build
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Location] interface.
 */
class LocationImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer
) : Location {

    private val currentOsVersion = Build.VERSION.SDK_INT

    /**
     * Enables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    override fun enableGps() {
        setLocationGpsEnabled(true)
        logger.i("GPS enabled")
    }

    /**
     * Disables GPS on the device.
     *
     * Required Permissions: INTERNET
     */
    override fun disableGps() {
        setLocationGpsEnabled(false)
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

    private fun setLocationGpsEnabled(enabled: Boolean) {
        if (currentOsVersion >= Build.VERSION_CODES.R) {
            val gpsEnabled = if (enabled) "3" else "0"
            adbServer.performShell("settings put secure location_mode $gpsEnabled")
        } else {
            val gpsEnabled = if (enabled) "+gps" else "-gps"
            adbServer.performShell("settings put secure location_providers_allowed $gpsEnabled")
        }
    }
}
