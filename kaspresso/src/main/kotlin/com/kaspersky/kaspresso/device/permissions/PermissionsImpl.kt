package com.kaspersky.kaspresso.device.permissions

import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.flakysafety.wait
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of Permissions interface.
 */
class PermissionsImpl(
    private val logger: UiTestLogger,
    private val uiDevice: UiDevice
) : Permissions {

    private val packageInstallerPackageName = "com.android.packageinstaller"

    private val permissionDenyButtonId = "$packageInstallerPackageName:id/permission_deny_button"
    private val permissionAllowButtonId = "$packageInstallerPackageName:id/permission_allow_button"

    private val buttonResNameMap = mapOf(
        Permissions.Button.ALLOW to getResIdWithPackageName("permission_allow_button"),
        Permissions.Button.ALLOW_ALWAYS to getResIdWithPackageName("permission_allow_always_button"),
        Permissions.Button.ALLOW_FOREGROUND to getResIdWithPackageName("permission_allow_foreground_only_button"),
        Permissions.Button.DENY to getResIdWithPackageName("permission_deny_button"))

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.
     */
    override fun allowViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(permissionAllowButtonId) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.
     */
    override fun denyViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(permissionDenyButtonId) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog
     */
    override fun clickOn(button: Permissions.Button) = wait(timeoutMs = 1_000) {
        handlePermissionRequest(buttonResNameMap[button])
    }

    /**
     * Passes the permission-requesting permissions dialog.
     *
     * @param buttonResId resource name of permission dialog button
     */
    private fun handlePermissionRequest(buttonResId: String?) {
        try {
            val btnSelector = UiSelector()
                .clickable(true)
                .checkable(false)
                .resourceId(buttonResId)

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

    private fun getResIdWithPackageName(resId: String): String {
        val packageName = "com.google.android.permissioncontroller"
        return "$packageName:id/$resId"
    }
}