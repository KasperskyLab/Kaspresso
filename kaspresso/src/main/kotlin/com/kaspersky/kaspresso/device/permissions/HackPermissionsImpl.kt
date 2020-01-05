package com.kaspersky.kaspresso.device.permissions

import android.app.UiAutomation
import android.os.Process
import android.os.UserHandle
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [HackPermissions] interface.
 */
class HackPermissionsImpl(
    private val uiAutomation: UiAutomation,
    private val logger: UiTestLogger
) : HackPermissions {

    /**
     * @return result of operation: true is success, false is something went wrong
     */
    override fun grant(packageName: String, permission: String): Boolean {
        logger.i("Attempt to grant permission=$permission for packageName=$packageName unfairly")
        return try {
            uiAutomation
                .javaClass
                .getDeclaredMethod(
                    "grantRuntimePermission",
                    String::class.java,
                    String::class.java,
                    UserHandle::class.java
                ).invoke(uiAutomation, packageName, permission, Process.myUserHandle())
            logger.i("Attempt to grant permission=$permission for packageName=$packageName unfairly was successful")
            true
        } catch (error: Throwable) {
            logger.e("Attempt to grant permission=$permission for packageName=$packageName unfairly failed with error=$error")
            false
        }
    }
}