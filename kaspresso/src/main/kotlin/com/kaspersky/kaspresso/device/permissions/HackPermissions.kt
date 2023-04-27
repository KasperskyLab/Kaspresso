package com.kaspersky.kaspresso.device.permissions

/**
 * The interface to grant any permissions (i.e. signature permissions) unfairly without any interaction with the user.
 */
interface HackPermissions {

    /**
     * @return result of operation: true is success, false is something went wrong
     */
    @Deprecated("Private API usage is forbidden when targeting API 30 and above")
    fun grant(packageName: String, permission: String): Boolean

    fun grantThroughAdb(packageName: String, permission: String)
}
