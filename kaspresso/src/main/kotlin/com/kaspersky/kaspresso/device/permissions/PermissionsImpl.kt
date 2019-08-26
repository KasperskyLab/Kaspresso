package com.kaspersky.kaspresso.device.permissions

import android.os.Build
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

    private val packageInstallerPackageName = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P)
        "com.android.permissioncontroller" else "com.android.packageinstaller"

    private val buttonResNameMap = mapOf(
        Permissions.Button.ALLOW to getResIdWithPackageName("permission_allow_button"),
        Permissions.Button.ALLOW_ALWAYS to getResIdWithPackageName("permission_allow_always_button"),
        Permissions.Button.ALLOW_FOREGROUND to getResIdWithPackageName("permission_allow_foreground_only_button"),
        Permissions.Button.DENY to getResIdWithPackageName("permission_deny_button")
    )

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.
     */
    override fun allowViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(Permissions.Button.ALLOW) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.
     */
    override fun denyViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(Permissions.Button.DENY) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog
     */
    override fun clickOn(button: Permissions.Button) = wait(timeoutMs = 1_000) {
        handlePermissionRequest(button)
    }

    /**
     * Passes the permission-requesting permissions dialog.
     *
     * @param buttonResId resource name of permission dialog button
     */
    private fun handlePermissionRequest(button: Permissions.Button) {
        try {
            val btnSelector = UiSelector()
                .clickable(true)
                .checkable(false)
                .resourceId(buttonResNameMap[button])

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
        return "$packageInstallerPackageName:id/$resId"
    }
}