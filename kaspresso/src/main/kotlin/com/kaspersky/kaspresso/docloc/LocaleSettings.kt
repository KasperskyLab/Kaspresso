package com.kaspersky.kaspresso.docloc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Process
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.util.*

internal class LocaleSettings(
    private val context: Context,
    private val logger: UiTestLogger
) {

    /**
     * Change locale for Android OS Settings.
     *
     * @return locale has been changed in Settings
     */
    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    fun changeLanguage(locale: Locale): Boolean {
        logger.i("Attempt to change locale for Android OS Settings started")
        if (isNotGrantedChangeConfigurationPermission()) {
            logger.i("Attempt to change locale for Android OS Settings failed")
            return false
        }
        return try {
            val cls = Class.forName("android.app.ActivityManagerNative")
            val amService = cls.getDeclaredMethod("getDefault").invoke(cls)
            val configuration = amService.javaClass.getDeclaredMethod("getConfiguration").invoke(amService) as Configuration
            configuration.javaClass.getDeclaredField("userSetLocale").setBoolean(configuration, true)
            configuration.javaClass.getDeclaredField("locale").set(configuration, locale)
            amService.javaClass.getDeclaredMethod("updateConfiguration", Configuration::class.java).invoke(amService, configuration)
            logger.i("Attempt to change locale for Android OS Settings was successful")
            true
        } catch (error: Throwable) {
            logger.e("Attempt to change locale for Android OS Settings failed with error=$error")
            false
        }
    }

    private fun isNotGrantedChangeConfigurationPermission(): Boolean {
        return context.checkPermission(Manifest.permission.CHANGE_CONFIGURATION, Process.myPid(), Process.myUid()) != PackageManager.PERMISSION_GRANTED
    }

}