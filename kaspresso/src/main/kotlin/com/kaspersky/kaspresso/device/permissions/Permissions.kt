package com.kaspersky.kaspresso.device.permissions

/**
 * An interface to work with permissions.
 */
interface Permissions {

    /**
     * Passes the permission-requesting permissions dialog.
     *
     * @param shouldAllowPermissions if set to true permissions will be allowed, otherwise will not.
     */
    fun handlePermissionRequest(shouldAllowPermissions: Boolean)
}