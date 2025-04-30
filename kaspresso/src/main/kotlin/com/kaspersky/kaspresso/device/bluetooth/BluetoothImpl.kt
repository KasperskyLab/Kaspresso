package com.kaspersky.kaspresso.device.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
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
class BluetoothImpl(
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
     * Toggles Bluetooth state
     * Tries, first and foremost, to send ADB command. If this attempt fails,
     * opens Android Settings screen and tries to switch Bluetooth setting thumb.
     */
    private fun toggleBluetooth(enable: Boolean) {
        if (isBluetoothNotSupported()) {
            logger.i("Bluetooth is not supported")
            return
        }
        if (!changeBluetoothStateUsingAdbServer(enable, BLUETOOTH_STATE_CHANGE_ROOT_CMD) &&
            !changeBluetoothStateUsingAdbServer(enable, BLUETOOTH_STATE_CHANGE_CMD)
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

    @Suppress("MagicNumber")
    private fun toggleBluetoothUsingAndroidSettings(enable: Boolean) {
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
        // Turn Bluetooth off or on via Quick Access Menu
        NotificationsFullScreen {
            bluetoothSwitch.setChecked(enable)
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
