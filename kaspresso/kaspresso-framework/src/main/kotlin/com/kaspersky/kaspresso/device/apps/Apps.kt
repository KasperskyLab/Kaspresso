package com.kaspersky.kaspresso.device.apps

import android.net.Uri
import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * The interface to work with installer, launcher and package manager.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
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
    @RequiresAdbServer
    fun install(apkPath: String)

    /**
     * Installs an app via ADB only if [packageName] is not installed
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of the app to be checked.
     * @param apkPath a path to the apk to be installed. The apk is hosted on the test server.
     */
    @RequiresAdbServer
    fun installIfNotExists(packageName: String, apkPath: String)

    /**
     * Uninstalls an app via ADB.
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of the app to be deleted.
     */
    @RequiresAdbServer
    fun uninstall(packageName: String)

    /**
     * Uninstalls an app via ADB only if it installed
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of an app to be deleted.
     */
    @RequiresAdbServer
    fun uninstallIfExists(packageName: String)

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
