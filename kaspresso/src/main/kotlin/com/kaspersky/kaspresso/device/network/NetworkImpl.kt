package com.kaspersky.kaspresso.device.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import com.kaspersky.kaspresso.device.server.AdbServer

/**
 * Default implementation of Network interface.
 */
class NetworkImpl(
    private val targetContext: Context
) : Network {

    /**
     *  Enables wi-fi and mobile data using adb.
     *
     *  Required Permissions: INTERNET.
     */
    override fun enable() {
        AdbServer.performAdb("shell svc data enable", "shell svc wifi enable")
    }

    /**
     *  Disables wi-fi and mobile data using adb.
     *
     *  Required Permissions: INTERNET.
     */
    override fun disable() {
        AdbServer.performAdb("shell svc data disable", "shell svc wifi disable")
    }

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    @SuppressLint("WifiManagerLeak")
    override fun toggleWiFi(enable: Boolean) {
        val wifiManager =
            if (isSdkVersionHigherThan(Build.VERSION_CODES.N)) {
                targetContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            } else {
                targetContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            }

        wifiManager.isWifiEnabled = enable
    }

    /**
     * Wraps an SDK version checks.
     */
    private fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}