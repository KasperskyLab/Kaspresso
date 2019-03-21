package com.kaspersky.kaspresso.device.internet

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device.isSdkVersionHigherThan
import com.kaspersky.kaspresso.device.server.AdbServer

/**
 * Default implementation of InternetManager interface.
 */
object InternetManagerImpl: InternetManager {

    private val targetContext: Context
        get() = InstrumentationRegistry.getTargetContext()

    private val adbServer: AdbServer = Configurator.adbServer

    /**
     *  Enables wi-fi and mobile data using adb.
     */
    override fun enableInternet() {
        adbServer.performAdb("shell svc data enable", "shell svc wifi enable")
    }

    /**
     *  Disables wi-fi and mobile data using adb.
     */
    override fun disableInternet() {
        adbServer.performAdb("shell svc data disable", "shell svc wifi disable")
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
}