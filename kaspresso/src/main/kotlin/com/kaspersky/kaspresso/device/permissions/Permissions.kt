package com.kaspersky.kaspresso.device.permissions

/**
 * The interface to work with permissions fairly by real permission dialogs.
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

    /**
     * Check the permission-requesting permissions dialog is visible.
     */
    fun isDialogVisible(): Boolean

    /**
     * Passes the permission-requesting permissions dialog
     */
    fun clickOn(button: Button)

    enum class Button {
        ALLOW,
        ALLOW_ALWAYS,
        ALLOW_FOREGROUND,
        DENY
    }
}
