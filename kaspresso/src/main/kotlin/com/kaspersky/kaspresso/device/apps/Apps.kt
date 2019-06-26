package com.kaspersky.kaspresso.device.apps

import android.net.Uri

/**
 * An interface to work with installer, launcher and package manager.
 */
interface Apps {

    companion object {
        private const val MAX_LAUNCH_TIME_MS = 10_000L
    }

    val targetAppLauncherPackageName: String
    val targetAppPackageName: String

    /**
     *  Installs an app via ADB.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param apkPath a path to an apk to be installed. The apk is hosted on the test server.
     */
    fun install(apkPath: String)

    /**
     *  Uninstalls an app via ADB.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param packageName an android package name of an app to be deleted.
     */
    fun uninstall(packageName: String)

    fun waitForLauncher(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        launcherPackageName: String = targetAppLauncherPackageName
    )

    fun waitForAppLaunchAndReady(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        packageName: String = targetAppPackageName
    )

    fun openUrlInChrome(url: String)

    fun launch(packageName: String, data: Uri? = null)

    fun openRecent(contentDescription: String)

    fun kill(packageName: String = targetAppPackageName)
}