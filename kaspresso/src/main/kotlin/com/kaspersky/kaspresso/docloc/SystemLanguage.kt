package com.kaspersky.kaspresso.docloc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Process
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.Locale

internal class SystemLanguage(
    private val context: Context,
    private val logger: UiTestLogger,
    private val hackPermissions: HackPermissions
) {

    /**
     * Changes locale for Android OS Settings.
     * Under the hood grants CHANGE_CONFIGURATION permission
     *     (without this permission, it's impossible to change system language)
     *
     * @throws Throwable if something went wrong
     */
    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    fun switch(locale: Locale) {
        logger.i("SystemLanguage: Installing new system language=$locale started")
        grantPermissionsIfNeed()
        try {
            val cls = Class.forName("android.app.ActivityManagerNative")
            val amService = cls.getDeclaredMethod("getDefault").invoke(cls)
            val configuration = amService.javaClass.getDeclaredMethod("getConfiguration").invoke(amService) as Configuration
            configuration.javaClass.getDeclaredField("userSetLocale").setBoolean(configuration, true)
            configuration.javaClass.getDeclaredField("locale").set(configuration, locale)
            amService.javaClass.getMethod("updateConfiguration", Configuration::class.java).invoke(amService, configuration)
            logger.i("SystemLanguage: Installing new system language=$locale completed")
        } catch (error: Throwable) {
            logger.e("SystemLanguage: Installing new system language=$locale failed with error=$error")
            throw error
        }
    }

    /**
     * Try to grant permission CHANGE_CONFIGURATION if the permission was not granted
     * @throws DocLocException In case of a failure to grant one
     */
    private fun grantPermissionsIfNeed() {
        val permissionStateAtTheBeginning = context.checkPermission(Manifest.permission.CHANGE_CONFIGURATION, Process.myPid(), Process.myUid())
        if (permissionStateAtTheBeginning == PackageManager.PERMISSION_GRANTED) {
            return
        }
        val attemptToGrantPermissionResult = hackPermissions.grant(context.packageName, Manifest.permission.CHANGE_CONFIGURATION)
        if (!attemptToGrantPermissionResult) {
            throw DocLocException(
                "SystemLanguage: The attempt to grant Manifest.permission.CHANGE_CONFIGURATION for SystemLanguage failed"
            )
        }
    }
}
