package com.kaspersky.kaspresso.device.permissions

/**
 * An interface to work with permissions.
 */
interface Permissions {

    /**
     * Passes the permission-requesting permissions dialog and allows permissions.
     */
    fun allowViaDialog()

    /**
     * Passes the permission-requesting permissions dialog and denies permissions.
     */
    fun denyViaDialog()
}