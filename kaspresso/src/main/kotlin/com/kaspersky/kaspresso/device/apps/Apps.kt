package com.kaspersky.kaspresso.device.apps

import android.net.Uri

/**
 * The interface to work with installer, launcher and package manager.
 */
interface Apps {

    companion object {
        private const val MAX_LAUNCH_TIME_MS = 10_000L
    }

    val targetAppLauncherPackageName: String
    val targetAppPackageName: String

    /**
     * Installs an app via ADB.
     *
     * Required Permissions: INTERNET.
     *
     * @param apkPath a path to the apk to be installed. The apk is hosted on the test server.
     */
    fun install(apkPath: String)

    /**
     * Uninstalls an app via ADB.
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of the app to be deleted.
     */
    fun uninstall(packageName: String)

    /**
     * Checks app is installed on device
     *
     * @param packageName an android package name of the app to be checked.
     * @return a [Boolean] of installation state
     */
    fun isInstalled(packageName: String): Boolean

    fun waitForLauncher(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        launcherPackageName: String = targetAppLauncherPackageName
    )

    fun waitForAppLaunchAndReady(
        timeout: Long = MAX_LAUNCH_TIME_MS,
        packageName: String = targetAppPackageName
    )

    /**
     * Opens the given [url] on Chrome.
     *
     * @param url the url to be opened.
     */
    fun openUrlInChrome(url: String)

    /**
     * Launches an app with given [packageName].
     *
     * @param packageName the package name of an app to launch.
     * @param data the data to put to the launch intent.
     */
    fun launch(packageName: String, data: Uri? = null)

    /**
     * Opens the app from the recent list by the description.
     *
     * @param contentDescription the description of the app to launch.
     */
    fun openRecent(contentDescription: String)

    /**
     * Kills the process of the app by the given [packageName].
     *
     * @param packageName the package name of the app to be killed.
     */
    fun kill(packageName: String = targetAppPackageName)
}