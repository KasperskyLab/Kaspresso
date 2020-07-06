package com.kaspersky.kaspresso.device.network

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.internal.systemscreen.DataUsageSettingsScreen
import com.kaspersky.kaspresso.internal.systemscreen.WiFiSettingsScreen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [Network] interface.
 */
class NetworkImpl(
    private val targetContext: Context,
    private val adbServer: AdbServer,
    logger: UiTestLogger
) : Network {

    companion object {
        private const val CMD_STATE_ENABLE = "enable"
        private const val CMD_STATE_DISABLE = "disable"
        private const val NETWORK_STATE_CMD = "svc data"
        private const val NETWORK_STATE_CHECK_CMD =
            "dumpsys telephony.registry | grep \"mDataConnectionState\""
        private const val WIFI_STATE_CMD = "svc wifi"
        private const val WIFI_STATE_CHECK_CMD = "dumpsys wifi | grep \"Wi-Fi is\""
        private const val NETWORK_STATE_CHECK_RESULT_ENABLED = "mDataConnectionState=2"
        private const val NETWORK_STATE_CHECK_RESULT_DISABLED = "mDataConnectionState=0"
        private val WIFI_STATE_CHECK_RESULT_ENABLED = Regex("Wi-Fi is enabl(ing|ed)")
        private val WIFI_STATE_CHECK_RESULT_DISABLED = Regex("Wi-Fi is disabl(ing|ed)")
        private val ADB_RESULT_REGEX = Regex("exitCode=(\\d+), message=(.+)")
    }

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(logger)

    /**
     * Enables both Wi-Fi and mobile data.
     *
     * Required Permissions: INTERNET.
     */
    override fun enable() {
        toggleMobileData(true)
        toggleWiFi(true)
    }

    /**
     * Disables both Wi-Fi and mobile data.
     *
     * Required Permissions: INTERNET.
     */
    override fun disable() {
        toggleMobileData(false)
        toggleWiFi(false)
    }

    /**
     * Toggles only mobile data. Tries to change state with adb command first and using
     * Settings then.
     */
    override fun toggleMobileData(enable: Boolean) {
        if (!toggleMobileDataUsingAdbServer(enable))
            return toggleMobileDataUsingAndroidSettings(enable)
    }

    private fun toggleMobileDataUsingAdbServer(enable: Boolean): Boolean = try {
        val (state, expectedResult) = when (enable) {
            true -> CMD_STATE_ENABLE to NETWORK_STATE_CHECK_RESULT_ENABLED
            false -> CMD_STATE_DISABLE to NETWORK_STATE_CHECK_RESULT_DISABLED
        }
        adbServer.performShell("$NETWORK_STATE_CMD $state")
        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(),
            action = { adbServer.performShell(NETWORK_STATE_CHECK_CMD) }
        )
        expectedResult == parseAdbResponse(result)?.trim()
    } catch (ex: AdbServerException) {
        false
    }

    private fun getParams(): FlakySafetyParams {
        return FlakySafetyParams.custom(
            timeoutMs = 500,
            intervalMs = 50,
            allowedExceptions = setOf(AdbServerException::class.java)
        )
    }

    private fun toggleMobileDataUsingAndroidSettings(enable: Boolean) {
        DataUsageSettingsScreen {
            open(targetContext)
            when (enable) {
                true -> enableMobileData()
                false -> disableMobileData()
            }
            close(targetContext)
        }
    }

    /**
     * Toggles only Wi-Fi. Tries, first and foremost, to change Wi-Fi state using Android API if targetApi
     * is lower than 29 and [Manifest.permission.CHANGE_WIFI_STATE] permission is granted. In case of
     * failure, sends ADB command. If this attempt fails too, opens Android Settings screen and tries
     * to switch Wi-Fi setting thumb.
     */
    override fun toggleWiFi(enable: Boolean) {
        if (!changeWiFiStateUsingAndroidApi(enable) && !changeWiFiStateUsingAdbServer(enable))
            changeWifiStateUsingAndroidSettings(enable)
    }

    /**
     * Tries to change Wi-Fi state, if application has [Manifest.permission.CHANGE_WIFI_STATE] and
     * targetApi is below [Build.VERSION_CODES.Q]
     * @return true if Wi-Fi state changed or false otherwise
     */
    private fun changeWiFiStateUsingAndroidApi(isEnabled: Boolean): Boolean {
        val targetSdkVersion = targetContext.applicationInfo.targetSdkVersion

        if (targetSdkVersion >= Build.VERSION_CODES.Q) return false
        if (ContextCompat.checkSelfPermission(
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
    private fun changeWiFiStateUsingAdbServer(isEnabled: Boolean): Boolean = try {
        val (state, expectedResult) = when (isEnabled) {
            true -> CMD_STATE_ENABLE to WIFI_STATE_CHECK_RESULT_ENABLED
            false -> CMD_STATE_DISABLE to WIFI_STATE_CHECK_RESULT_DISABLED
        }
        adbServer.performShell("$WIFI_STATE_CMD $state")
        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(),
            action = { adbServer.performShell(WIFI_STATE_CHECK_CMD) }
        )
        val succeed = expectedResult.matches(parseAdbResponse(result) ?: "")
        if (succeed) true else throw AdbServerException("Failed to change Wi-Fi state using ABD")
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
