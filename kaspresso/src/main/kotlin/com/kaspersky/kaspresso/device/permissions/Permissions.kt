package com.kaspersky.kaspresso.device.permissions

/**
 * The interface to work with permissions fairly by real permission dialogs.
 */
interface Permissions {

    /**
     * Passes the permission-requesting permissions dialog and allows permissions.
     *
     * @param button - the button which would be pressed. Changing the default value may be useful in android 11+ cases
     * @see (https://developer.android.com/about/versions/11/privacy/location and https://developer.android.com/about/versions/14/changes/partial-photo-video-access)
     */
    fun allowViaDialog(button: Button = Button.ALLOW)

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
        ALLOW_ALL,
        ALLOW_SELECTED,
        DENY
    }
}
