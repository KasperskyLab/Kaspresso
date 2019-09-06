package com.kaspersky.kaspresso.device.permissions

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.internal.wait.wait
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of Permissions interface.
 */
class PermissionsImpl(
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : Permissions {

    private companion object {
        private const val DIALOG_TIMEOUT_MS: Long = 1_000
    }

    private val packageInstallerPackageName = "com.android.packageinstaller"
    private val permissionDenyButtonId = "$packageInstallerPackageName:id/permission_deny_button"
    private val permissionAllowButtonId = "$packageInstallerPackageName:id/permission_allow_button"

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.
     */
    override fun allowViaDialog() =
        wait(timeoutMs = DIALOG_TIMEOUT_MS, logger = logger) { handlePermissionRequest(shouldAllowPermissions = true) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.
     */
    override fun denyViaDialog() =
        wait(timeoutMs = DIALOG_TIMEOUT_MS, logger = logger) { handlePermissionRequest(shouldAllowPermissions = false) }

    /**
     * Passes the permission-requesting permissions dialog.
     *
     * @param shouldAllowPermissions if set to true permissions will be allowed, otherwise will not.
     */
    private fun handlePermissionRequest(shouldAllowPermissions: Boolean) {
        try {
            val btnSelector = UiSelector()
                .clickable(true)
                .checkable(false)
                .resourceId(
                    if (shouldAllowPermissions)
                        permissionAllowButtonId
                    else
                        permissionDenyButtonId
                )

            val btn = uiDevice.findObject(btnSelector)

            if (btn.exists()) {
                btn.click()
            }
        } catch (e: UiObjectNotFoundException) {
            logger.e("There are no permissions dialog to interact with.")
        } catch (e: Throwable) {
            logger.e(e.getStackTraceAsString())
            throw e
        }
    }
}