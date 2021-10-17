package com.kaspersky.kaspresso.device.network

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.kaspersky.components.kautomator.system.UiSystem
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.internal.systemscreen.DataUsageSettingsScreen
import com.kaspersky.kaspresso.internal.systemscreen.NotificationsFullScreen
import com.kaspersky.kaspresso.internal.systemscreen.NotificationsMobileDataScreen
import com.kaspersky.kaspresso.internal.systemscreen.NotificationsShortScreen
import com.kaspersky.kaspresso.internal.systemscreen.WiFiSettingsScreen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [Network] interface.
 */
class NetworkImpl(
    private val logger: UiTestLogger,
    private val targetContext: Context,
    private val adbServer: AdbServer
) : Network {

    companion object {
        private const val CMD_STATE_ENABLE = "enable"
        private const val CMD_STATE_DISABLE = "disable"
        private const val NETWORK_STATE_CHANGE_CMD = "svc data"
        private const val NETWORK_STATE_CHANGE_ROOT_CMD = "su 0 svc data"
        private const val NETWORK_STATE_CHECK_CMD = "settings get global mobile_data"
        private const val NETWORK_STATE_CHECK_RESULT_ENABLED = "1"
        private const val NETWORK_STATE_CHECK_RESULT_DISABLED = "0"
        private const val WIFI_STATE_CHANGE_CMD = "svc wifi"
        private const val WIFI_STATE_CHANGE_ROOT_CMD = "su 0 svc wifi"
        private const val WIFI_STATE_CHECK_CMD = "settings get global wifi_on"
        private const val WIFI_STATE_CHECK_RESULT_ENABLED = "1"
        private const val WIFI_STATE_CHECK_RESULT_DISABLED = "0"
        private val ADB_RESULT_REGEX = Regex("exitCode=(\\d+), message=(.+)")
    }

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(logger)
    private val currentOsVersion = Build.VERSION.SDK_INT
    private val flakySafetyParams: FlakySafetyParams
        get() = FlakySafetyParams(
            timeoutMs = 1000,
            intervalMs = 100,
            allowedExceptions = setOf(AdbServerException::class.java)
        )

    /**
     * Enables both Wi-Fi and mobile data.
     *
     * Required Permissions: INTERNET.
     */
    override fun enable() {
        toggleMobileData(true)
        toggleWiFi(true)
        logger.i("Enable wi-fi and mobile data")
    }

    /**
     * Disables both Wi-Fi and mobile data.
     *
     * Required Permissions: INTERNET.
     */
    override fun disable() {
        toggleMobileData(false)
        toggleWiFi(false)
        logger.i("Disable wi-fi and mobile data")
    }

    /**
     * Toggles only mobile data. Tries to change state with adb command first and using
     * Settings then.
     */
    override fun toggleMobileData(enable: Boolean) {
        if (!toggleMobileDataUsingAdbServer(enable, NETWORK_STATE_CHANGE_ROOT_CMD) &&
            !toggleMobileDataUsingAdbServer(enable, NETWORK_STATE_CHANGE_CMD)
        ) {
            toggleMobileDataUsingAndroidSettings(enable)
            logger.i("Mobile data ${if (enable) "en" else "dis"}abled")
        }
    }

    private fun toggleMobileDataUsingAdbServer(enable: Boolean, changeCommand: String): Boolean =
        try {
            val (state, expectedResult) = when (enable) {
                true -> CMD_STATE_ENABLE to NETWORK_STATE_CHECK_RESULT_ENABLED
                false -> CMD_STATE_DISABLE to NETWORK_STATE_CHECK_RESULT_DISABLED
            }
            adbServer.performShell("$changeCommand $state")
            flakySafetyAlgorithm.invokeFlakySafely(flakySafetyParams) {
                val result = adbServer.performShell(NETWORK_STATE_CHECK_CMD)
                if (parseAdbResponse(result)?.trim() == expectedResult) true else
                    throw AdbServerException("Failed to change network state using ABD")
            }
        } catch (ex: AdbServerException) {
            false
        }

    private fun toggleMobileDataUsingAndroidSettings(enable: Boolean) {
        if (currentOsVersion < Build.VERSION_CODES.N) {
            toggleMobileDataUsingAndroidSettingsOnLowAndroid(enable)
        } else {
            toggleMobileDataUsingAndroidSettingsOnHighAndroid(enable)
        }
    }

    private fun toggleMobileDataUsingAndroidSettingsOnHighAndroid(enable: Boolean) {
        DataUsageSettingsScreen {
            open(targetContext)
            when (enable) {
                true -> enableMobileData()
                false -> disableMobileData()
            }
            close(targetContext)
        }
    }

    @Suppress("MagicNumber")
    private fun toggleMobileDataUsingAndroidSettingsOnLowAndroid(enable: Boolean) {
        val height = targetContext.resources.displayMetrics.heightPixels
        val width = targetContext.resources.displayMetrics.widthPixels

        UiSystem {
            drag(width / 2, 0, width / 2, (height * 0.67).toInt(), 50)
        }
        NotificationsShortScreen {
            mainNotification.click()
        }
        NotificationsFullScreen {
            mobileDataSwitch.click()
        }
        NotificationsMobileDataScreen {
            when (enable) {
                true -> mobileDataSwitch.setChecked(true)
                false -> mobileDataSwitch.setChecked(false)
            }
        }
        UiSystem {
            drag(width / 2, height, width / 2, 0, 50)
        }
    }

    /**
     * Toggles only Wi-Fi.
     * Tries, first and foremost, to change Wi-Fi state using Android API if targetApi
     * is lower than 29 and [Manifest.permission.CHANGE_WIFI_STATE] permission is granted. In case of
     * failure, sends ADB command. If this attempt fails too, opens Android Settings screen and tries
     * to switch Wi-Fi setting thumb.
     */
    override fun toggleWiFi(enable: Boolean) {
        if (!changeWiFiStateUsingAndroidApi(enable) &&
            !changeWiFiStateUsingAdbServer(enable, WIFI_STATE_CHANGE_ROOT_CMD) &&
            !changeWiFiStateUsingAdbServer(enable, WIFI_STATE_CHANGE_CMD)
        ) {
            changeWifiStateUsingAndroidSettings(enable)
            logger.i("Wi-fi ${if (enable) "en" else "dis"}abled")
        }
    }

    /**
     * Tries to change Wi-Fi state, if application has [Manifest.permission.CHANGE_WIFI_STATE] and
     * targetApi is below [Build.VERSION_CODES.Q]
     * @return true if Wi-Fi state changed or false otherwise
     */
    private fun changeWiFiStateUsingAndroidApi(isEnabled: Boolean): Boolean {
        val targetSdkVersion = targetContext.applicationInfo.targetSdkVersion

        if (targetSdkVersion >= Build.VERSION_CODES.Q) return false
        if (
            ContextCompat.checkSelfPermission(
                targetContext,
                Manifest.permission.CHANGE_WIFI_STATE
            ) == PackageManager.PERMISSION_DENIED
        ) return false

        val manager =
            (targetContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager)
        if (manager.isWifiEnabled == isEnabled) return true

        @Suppress("DEPRECATION")
        manager.isWifiEnabled = isEnabled

        return manager.isWifiEnabled == isEnabled
    }

    /**
     * Tries to change Wi-Fi state using AdbServer if it is available
     * @return true if Wi-Fi state changed or false otherwise
     */
    private fun changeWiFiStateUsingAdbServer(isEnabled: Boolean, changeCommand: String): Boolean =
        try {
            val (state, expectedResult) = when (isEnabled) {
                true -> CMD_STATE_ENABLE to WIFI_STATE_CHECK_RESULT_ENABLED
                false -> CMD_STATE_DISABLE to WIFI_STATE_CHECK_RESULT_DISABLED
            }
            adbServer.performShell("$changeCommand $state")
            flakySafetyAlgorithm.invokeFlakySafely(flakySafetyParams) {
                val result = adbServer.performShell(WIFI_STATE_CHECK_CMD)
                if (parseAdbResponse(result)?.trim() == expectedResult) true else
                    throw AdbServerException("Failed to change Wi-Fi state using ABD")
            }
        } catch (e: AdbServerException) {
            false
        }

    /**
     * Toggles Wi-Fi thumb in Android settings.
     */
    private fun changeWifiStateUsingAndroidSettings(isEnabled: Boolean) {
        WiFiSettingsScreen {
            open(targetContext)
            when (isEnabled) {
                true -> enableWifi()
                false -> disableWifi()
            }
            close(targetContext)
        }
    }

    private fun parseAdbResponse(response: List<String>): String? {
        val result = response.firstOrNull()?.lineSequence()?.first() ?: return null
        val match = ADB_RESULT_REGEX.find(result) ?: return null
        val (_, message) = match.destructured
        return message
    }
}
