package com.kaspersky.kaspresso.device.permissions

/**
 * An interface to work with permissions.
 */
interface Permissions {

    /**
     * Passes the permission-requesting permissions dialog and allows permissions.
     */
    @Deprecated("use Permissions.clickOn")
    fun allowViaDialog()

    /**
     * Passes the permission-requesting permissions dialog and denies permissions.
     */
    @Deprecated("use Permissions.clickOn")
    fun denyViaDialog()
    
     /**
     * Passes the permission-requesting permissions dialog and returns true if visible.
     */
    fun allowisVisibleDialog():Boolean

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
