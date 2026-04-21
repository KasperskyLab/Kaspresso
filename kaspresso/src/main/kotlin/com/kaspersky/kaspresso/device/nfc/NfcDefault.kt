package com.kaspersky.kaspresso.device.nfc

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Until
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Nfc] interface.
 *
 * Toggle strategy (tried in order):
 * 1. `svc nfc enable|disable` via AdbServer — works on most devices since API 17.
 * 2. Android Settings NFC screen as a last resort.
 *
 * @param logger logger instance provided by the framework.
 * @param targetContext target application context, used to check NFC hardware presence
 *   and to open Settings UI as a fallback.
 * @param instrumentalDependencyProvider provides [androidx.test.uiautomator.UiDevice]
 *   for Settings UI interaction fallback.
 * @param adbServer AdbServer instance to run shell commands.
 * @param uiSelectorsOverride optional override for default UI selectors used in Settings UI interaction.
 */
open class NfcDefault(
    private val logger: UiTestLogger,
    private val targetContext: Context,
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider,
    private val adbServer: AdbServer,
    private val uiSelectorsOverride: List<BySelector>? = null,
) : Nfc {

    companion object {
        private const val NFC_STATE_CHECK_CMD = "settings get global nfc_adapter_state"
        private const val NFC_STATE_CHANGE_CMD = "svc nfc"
        private const val NFC_SETTINGS_ACTION = "android.settings.NFC_SETTINGS"
        private const val SETTINGS_TIMEOUT_MS = 3_000L
    }

    override fun enable() {
        logger.i("Enable NFC")
        toggle(enable = true)
    }

    override fun disable() {
        logger.i("Disable NFC")
        toggle(enable = false)
    }

    override fun isEnabled(): Boolean {
        if (!isNfcHardwarePresent()) return false

        val manager = targetContext.getSystemService(Context.NFC_SERVICE) as NfcManager
        val adapter = manager.defaultAdapter ?: return false

        return adapter.isEnabled
    }

    /**
     * Toggles NFC state.
     * Tries `svc nfc` via AdbServer first; falls back to Settings UI if that fails.
     */
    private fun toggle(enable: Boolean) {
        if (!isNfcHardwarePresent()) {
            logger.i("NFC hardware is not present on this device")
            return
        }

        if (enable == isEnabled()) {
            logger.i("NFC is already in the required state")
            return
        }

        if (!changeNfcStateUsingAdbServer(enable)) {
            changeNfcStateUsingSettings(enable)
        }

        logger.i(if (isEnabled()) "NFC enabled" else "NFC disabled")
    }

    /**
     * Tries `svc nfc enable|disable` via AdbServer.
     * @return `true` if the state changed to the desired value, `false` on any failure.
     */
    @Suppress("MagicNumber")
    private fun changeNfcStateUsingAdbServer(enable: Boolean): Boolean {
        val cmd = if (enable) "enable" else "disable"
        return try {
            adbServer.performShell("$NFC_STATE_CHANGE_CMD $cmd")
            // Give the adapter a moment to apply the change, then verify
            Thread.sleep(500)
            isEnabled() == enable
        } catch (e: AdbServerException) {
            logger.i("svc nfc $cmd failed: ${e.message}")
            false
        }
    }

    /**
     * Opens the NFC settings screen and taps the NFC toggle via UiAutomator.
     * Used as a last resort when AdbServer is unavailable or the `svc` command fails.
     */
    private fun changeNfcStateUsingSettings(enable: Boolean) {
        logger.i("Falling back to Settings UI to toggle NFC")
        val uiDevice = instrumentalDependencyProvider.uiDevice

        val intent = Intent(NFC_SETTINGS_ACTION).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        targetContext.startActivity(intent)

        // Try to find an NFC toggle by common resource-id patterns across OEMs
        val toggleSelectors = uiSelectorsOverride ?: listOf(
            By.checkable(true).res("android:id/switch_widget").pkg("com.android.settings"),
            By.checkable(true).res("com.android.settings:id/switchWidget"),
            By.checkable(true).res("com.android.settings:id/sesl_switchbar_switch"), // samsung
            By.checkable(true).res("com.android.settings:id/settingslib_main_switch_bar"), // xiaomi
        )

        val toggle = toggleSelectors.firstNotNullOfOrNull { selector ->
            uiDevice.wait(Until.findObject(selector), SETTINGS_TIMEOUT_MS)
        }

        if (toggle != null) {
            if (toggle.isChecked != enable) {
                toggle.click()
            }
        } else {
            logger.i("Could not find NFC toggle in Settings UI")
        }

        uiDevice.pressBack()
    }

    private fun isNfcHardwarePresent(): Boolean =
        NfcAdapter.getDefaultAdapter(targetContext) != null

    private fun parseAdbResult(lines: List<String>): String? =
        lines.firstOrNull()?.lineSequence()?.firstOrNull()
}
