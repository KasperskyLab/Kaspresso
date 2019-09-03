package com.kaspersky.kaspresso.device.location

import com.kaspersky.kaspresso.device.server.AdbServerWrapper

class LocationImpl : Location {

    /**
     *  Enables GPS on the device.
     *
     *  Required Permissions: INTERNET
     */
    override fun enableGps() {
        setLocationProviders("+gps")
    }

    /**
     *  Disables GPS on the device.
     *
     *  Required Permissions: INTERNET
     */
    override fun disableGps() {
        setLocationProviders("-gps")
    }

    /**
     *  Sets current location.
     *
     *  Required Permissions: INTERNET
     */
    override fun setLocation(lat: Double, lon: Double) {
        AdbServerWrapper.performAdb("emu geo fix $lon $lat") // geo fix uses Lon-Lat, almost everyone uses Lat-Lon
    }

    private fun setLocationProviders(providers: String) {
        AdbServerWrapper.performShell("settings put secure location_providers_allowed $providers")
    }
}
