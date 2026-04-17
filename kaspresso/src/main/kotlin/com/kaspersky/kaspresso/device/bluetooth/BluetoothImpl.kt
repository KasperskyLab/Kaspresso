package com.kaspersky.kaspresso.device.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.view.KeyEvent
import com.kaspersky.components.kautomator.system.UiSystem
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.internal.systemscreen.NotificationsFullScreen
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [Bluetooth] interface.
 */
internal class BluetoothImpl(
    private val logger: UiTestLogger,
    private val targetContext: Context,
    private val adbServer: AdbServer
) : Bluetooth {

    companion object {
        private const val CMD_STATE_ENABLE = "enable"
        private const val CMD_STATE_DISABLE = "disable"
        private const val BLUETOOTH_STATE_CHANGE_CMD = "svc bluetooth"
        private const val BLUETOOTH_STATE_CHANGE_ROOT_CMD = "su 0 svc bluetooth"
        private const val BLUETOOTH_STATE_CHECK_CMD = "settings get global bluetooth_on"
        private const val BLUETOOTH_STATE_CHECK_RESULT_ENABLED = "1"
        private const val BLUETOOTH_STATE_CHECK_RESULT_DISABLED = "0"
        private val ADB_RESULT_REGEX = Regex("exitCode=(\\d+), message=(.+)")
    }

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(logger)
    private val flakySafetyParams: FlakySafetyParams
        get() = FlakySafetyParams(
            timeoutMs = 1000,
            intervalMs = 100,
            allowedExceptions = setOf(AdbServerException::class.java)
        )

    override fun enable() {
        logger.i("Enable bluetooth")
        toggleBluetooth(enable = true)
    }

    override fun disable() {
        logger.i("Disable bluetooth")
        toggleBluetooth(enable = false)
    }

    /**
     * Toggles Bluetooth state.
     * Tries in order:
     * 1. Android API — requires [Manifest.permission.BLUETOOTH_ADMIN] below API 31,
     *    [Manifest.permission.BLUETOOTH_CONNECT] on API 31–32. Restricted for third-party
     *    apps on [Build.VERSION_CODES.TIRAMISU]+.
     * 2. ADB `svc bluetooth` command via AdbServer (available since API 17).
     * 3. Android Settings UI as a last resort.
     */
    private fun toggleBluetooth(enable: Boolean) {
        if (isBluetoothNotSupported()) {
            logger.i("Bluetooth is not supported")
            return
        }

        if (enable == isBluetoothEnabled()) {
            logger.i("Bluetooth already in a needed state")
            return
        }

        if (!changeBluetoothStateUsingAndroidApi(enable) &&
            !changeBluetoothStateUsingAdbServer(enable, BLUETOOTH_STATE_CHANGE_CMD) &&
            !changeBluetoothStateUsingAdbServer(enable, BLUETOOTH_STATE_CHANGE_ROOT_CMD)
        ) {
            toggleBluetoothUsingAndroidSettings(enable)
        }

        if (isBluetoothEnabled()) {
            logger.i("Bluetooth enabled")
        } else {
            logger.i("Bluetooth disabled")
        }
    }

    /**
     * Tries to change Bluetooth state using Android API.
     * Works only if API level is below [Build.VERSION_CODES.TIRAMISU] and the required
     * permission ([Manifest.permission.BLUETOOTH_CONNECT] on API >= [Build.VERSION_CODES.S],
     * [Manifest.permission.BLUETOOTH_ADMIN] on lower APIs) is granted.
     * @return true if Bluetooth state changed or false otherwise
     */
    @SuppressLint("MissingPermission")
    @Suppress("DEPRECATION")
    private fun changeBluetoothStateUsingAndroidApi(isEnabled: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) return false

        val requiredPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Manifest.permission.BLUETOOTH_CONNECT
        } else {
            Manifest.permission.BLUETOOTH_ADMIN
        }
        if (ContextCompat.checkSelfPermission(targetContext, requiredPermission) == PackageManager.PERMISSION_DENIED) return false

        val adapter = getBluetoothAdapter() ?: return false
        if (adapter.isEnabled == isEnabled) return true

        return if (isEnabled) adapter.enable() else adapter.disable()
    }

    /**
     * Tries to change Bluetooth state using AdbServer if it is available
     * @return true if Bluetooth state changed or false otherwise
     */
    private fun changeBluetoothStateUsingAdbServer(isEnabled: Boolean, changeCommand: String): Boolean =
        try {
            val (state, expectedResult) = when (isEnabled) {
                true -> CMD_STATE_ENABLE to BLUETOOTH_STATE_CHECK_RESULT_ENABLED
                false -> CMD_STATE_DISABLE to BLUETOOTH_STATE_CHECK_RESULT_DISABLED
            }
            adbServer.performShell("$changeCommand $state")
            flakySafetyAlgorithm.invokeFlakySafely(flakySafetyParams) {
                val result = adbServer.performShell(BLUETOOTH_STATE_CHECK_CMD)
                if (parseAdbResponse(result)?.trim() == expectedResult) true else
                    throw AdbServerException("Failed to change Bluetooth state using ABD")
            }
        } catch (e: AdbServerException) {
            false
        }

    private fun toggleBluetoothUsingAndroidSettings(enable: Boolean) {
        if (enable) {
            enableBluetoothViaDialog()
        } else {
            disableBluetoothViaQuickSettings()
        }
    }

    private fun enableBluetoothViaDialog() {
        // Opening a dialog with the permission to turn on bluetooth
        adbServer.performShell("am", listOf("start -a android.bluetooth.adapter.action.REQUEST_ENABLE"))
        // Move cursor to "Allow" button
        adbServer.performShell("input", listOf("keyevent ${KeyEvent.KEYCODE_DPAD_RIGHT}"))
        adbServer.performShell("input", listOf("keyevent ${KeyEvent.KEYCODE_DPAD_RIGHT}"))
        // Clicking the "Allow" button
        adbServer.performShell("input", listOf("keyevent ${KeyEvent.KEYCODE_ENTER}"))
    }

    @Suppress("MagicNumber")
    private fun disableBluetoothViaQuickSettings() {
        val height = targetContext.resources.displayMetrics.heightPixels
        val width = targetContext.resources.displayMetrics.widthPixels

        // Swipe down on the screen to open the Quick Access Menu
        UiSystem {
            drag(width / 2, 0, width / 2, (height * 0.67).toInt(), 50)
        }
        // Swipe down again for more options. This is necessary because sometimes the bluetooth switch is located in additional settings
        UiSystem {
            drag(width / 2, 0, width / 2, (height * 0.67).toInt(), 50)
        }
        // Turn Bluetooth off via Quick Access Menu
        NotificationsFullScreen {
            bluetoothSwitch.setChecked(false)
        }
        // Swipe up to close additional settings
        UiSystem {
            drag(width / 2, height, width / 2, 0, 50)
        }
        // Swipe up again to close Quick Access Menu
        UiSystem {
            drag(width / 2, height, width / 2, 0, 50)
        }
    }

    private fun isBluetoothNotSupported(): Boolean =
        getBluetoothAdapter() == null

    private fun isBluetoothEnabled(): Boolean =
        getBluetoothAdapter()?.isEnabled ?: false

    private fun getBluetoothAdapter(): BluetoothAdapter? =
        (this.targetContext.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager)?.adapter

    private fun parseAdbResponse(response: List<String>): String? {
        val result = response.firstOrNull()?.lineSequence()?.first() ?: return null
        val match = ADB_RESULT_REGEX.find(result) ?: return null
        val (_, message) = match.destructured
        return message
    }
}
