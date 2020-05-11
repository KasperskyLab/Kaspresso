package com.kaspersky.kaspresso.device.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Build
import com.kaspersky.components.kautomator.system.UiSystem
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.internal.outscreens.NotificationScreen
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Network] interface.
 */
class NetworkImpl(
    private val targetContext: Context,
    private val adbServer: AdbServer,
    private val logger: UiTestLogger
) : Network {

    /**
     * Enables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    override fun enable() {
        toggleMobileData(true)
        toggleWiFi(true)
    }

    /**
     * Disables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    override fun disable() {
        toggleMobileData(false)
        toggleWiFi(false)
    }

    /**
     * Toggles only mobile data. Note: it works only if flight mode is off.
     */
    override fun toggleMobileData(enable: Boolean) {
        if (enable) adbServer.performAdb("shell svc data enable")
        else adbServer.performAdb("shell svc data disable")
    }

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    @Suppress("detekt.ComplexCondition")
    override fun toggleWiFi(enable: Boolean) {
        logger.i("NetworkImpl.toggleWifi(enable=$enable) starting...")

        val wifiState = isWifiEnabled()
        logger.i("NetworkImpl.toggleWifi(enable=$enable): current wi-fi state = $wifiState")
        if ((wifiState && enable) || (!wifiState && !enable)) {
            logger.i("NetworkImpl.toggleWifi(enable=$enable): wi-fi state has not been changed because this state is in the actual value")
            return
        }

        logger.i("NetworkImpl.toggleWifi(enable=$enable): program way (via WFiManager) started")
        val wifiManager = targetContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val requestState = wifiManager.setWifiEnabled(enable)
        logger.i("NetworkImpl.toggleWifi(enable=$enable): program way (via WFiManager) result = $requestState")
        if (requestState) {
            logger.i("NetworkImpl.toggleWifi(enable=$enable) finished.")
            return
        }

        logger.i("NetworkImpl.toggleWifi(enable=$enable): program way (via WFiManager) failed")
        logger.i("NetworkImpl.toggleWifi(enable=$enable): UI way (via Kautomator) started")
        toggleWifiViaUi(enable)

        logger.i("NetworkImpl.toggleWifi(enable=$enable) finished.")
    }

    private fun toggleWifiViaUi(enable: Boolean) {
        val finalText = if (enable) "On" else "Off"
        UiSystem {
            openNotification()
        }
        NotificationScreen {
            wifiSwitcher {
                click()
                // verify success network turning
                hasText(finalText)
            }
            pressBack()
        }
    }

    private fun isWifiEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val connectivityManager: ConnectivityManager? = targetContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val wifiNetworkInfo: NetworkInfo = connectivityManager?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                ?: throw DeviceNetworkException("targetContext.getSystemService(Context.CONNECTIVITY_SERVICE) is null")
            wifiNetworkInfo.isConnected
        } else {
            targetContext.getSystemService(WifiManager::class.java)?.isWifiEnabled ?: false
        }
    }
}
