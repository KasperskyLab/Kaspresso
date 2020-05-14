package com.kaspersky.kaspresso.device.network

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.internal.systemscreen.WiFiSettingsScreen
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Network] interface.
 */
class NetworkImpl(
    private val targetContext: Context,
    private val adbServer: AdbServer,
    private val logger: UiTestLogger
) : Network {

    companion object {
        private const val CMD_STATE_ENABLE = "enable"
        private const val CMD_STATE_DISABLE = "disable"
        private const val NETWORK_STATE_CMD = "svc data"
        private const val WIFI_STATE_CMD = "svc wifi"
        private const val WIFI_STATE_CHECK_CMD = "dumpsys wifi | grep \"Wi-Fi is\""
        private const val WIFI_STATE_CHECK_RESULT_ENABLED = "Wi-Fi is enabled"
        private const val WIFI_STATE_CHECK_RESULT_DISABLED = "Wi-Fi is disabled"
    }

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
        val state = when(enable) {
            true -> CMD_STATE_ENABLE
            false -> CMD_STATE_DISABLE
        }
        adbServer.performShell("$NETWORK_STATE_CMD $state")
    }

    /**
     * Toggles only wi-fi
     */
    override fun toggleWiFi(enable: Boolean) {
        if (!changeWiFiStateUsingAndroidApi(enable) || !changeWiFiStateUsingAdbServer(enable))
            changeWifiStateUsingAndroidSettings(enable)
    }

    /**
     * Tries to change WiFi state if application has [Manifest.permission.CHANGE_WIFI_STATE] and
     * target api is below [Build.VERSION_CODES.Q]
     * @return true if wifi state changed or false otherwise
     */
    private fun changeWiFiStateUsingAndroidApi(isEnabled: Boolean): Boolean {
        val targetSdkVersion = targetContext.applicationInfo.targetSdkVersion

        if (targetSdkVersion >= Build.VERSION_CODES.Q) return false
        if (ContextCompat.checkSelfPermission(targetContext, Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_DENIED) return false

        val manager = (targetContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager)
        if (manager.isWifiEnabled == isEnabled) return true

        @Suppress("DEPRECATION")
        manager.isWifiEnabled = isEnabled

        return manager.isWifiEnabled == isEnabled
    }

    /**
     * Tries to change WiFi state using AdbServer if it is available
     * @return true if wifi state changed or false otherwise
     */
    private fun changeWiFiStateUsingAdbServer(isEnabled: Boolean): Boolean {
        return try {
            val (state, expectedResult) = when (isEnabled) {
                true -> CMD_STATE_ENABLE to WIFI_STATE_CHECK_RESULT_ENABLED
                false -> CMD_STATE_DISABLE to WIFI_STATE_CHECK_RESULT_DISABLED
            }
            adbServer.performShell("$WIFI_STATE_CMD $state")
            val result = adbServer.performShell(WIFI_STATE_CHECK_CMD).firstOrNull()
            return expectedResult == result
        } catch (e: AdbServerException) {
            false
        }
    }

    /**
     * The last chance. Tries to change WiFi state using Android settings
     */
    private fun changeWifiStateUsingAndroidSettings(isEnabled: Boolean) {
        WiFiSettingsScreen {
            open(targetContext)
            if (isEnabled) {
                enableWifi()
            } else {
                disableWifi()
            }
            close(targetContext)
        }
    }
}
