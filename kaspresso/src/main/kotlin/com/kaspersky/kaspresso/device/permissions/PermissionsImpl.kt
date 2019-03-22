package com.kaspersky.kaspresso.device.permissions

import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.getStackTraceAsString

/**
 * An implementation of Permissions interface.
 */
object PermissionsImpl : Permissions {

    private const val PACKAGE_INSTALLER_PACKAGE_NAME = "com.android.packageinstaller"
    private const val PERMISSION_DENY_BUTTON_ID = "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_deny_button"
    private const val PERMISSION_ALLOW_BUTTON_ID = "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_allow_button"

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Passes the permission-requesting permissions dialog and allows permissions.
     */
    override fun allowViaDialog() = handlePermissionRequest(shouldAllowPermissions = true)

    /**
     * Passes the permission-requesting permissions dialog and denies permissions.
     */
    override fun denyViaDialog() = handlePermissionRequest(shouldAllowPermissions = false)

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
                        PERMISSION_ALLOW_BUTTON_ID
                    else
                        PERMISSION_DENY_BUTTON_ID
                )

            val btn = Device.uiDevice.findObject(btnSelector)

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