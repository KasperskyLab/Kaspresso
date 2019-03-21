package com.kaspersky.kaspresso.device.apps

import android.net.Uri

interface AppsManager {

    val targetAppLauncherPackageName: String
    val targetAppPackageName: String

    /**
     *  Installs an app thorough ADB.
     *
     *  @param apkPath path to an apk to be installed. The apk is hosted on the test server.
     */
    fun installApp(apkPath: String)

    /**
     *  Uninstalls an app thorough ADB.
     *
     *  @param pkg android package name of an app to be deleted.
     */
    fun uninstallApp(packageName: String)

    fun waitForLauncher(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        launcherPackageName: String = targetAppLauncherPackageName
    )

    fun waitForAppLaunchAndReady(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        packageName: String = targetAppPackageName
    )

    fun openUrlInChrome(url: String)

    fun launchApp(packageName: String, data: Uri? = null)

    fun openRecentApp(contentDescription: String)

    fun killApp(
        packageName: String = targetAppPackageName
    )
}

private const val MAX_LAUNCH_TIME_MS = 10_000L