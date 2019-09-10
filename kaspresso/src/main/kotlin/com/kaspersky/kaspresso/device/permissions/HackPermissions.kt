package com.kaspersky.kaspresso.device.permissions

/**
 * An interface to grant any permissions (i.e. signature permissions) unfairly without any interaction with the user.
 */
interface HackPermissions {

    /**
     * @return result of operation: true is success, false is something went wrong
     */
    fun grant(packageName: String, permission: String): Boolean
}