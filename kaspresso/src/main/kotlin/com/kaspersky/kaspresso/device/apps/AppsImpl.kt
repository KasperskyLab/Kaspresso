package com.kaspersky.kaspresso.device.apps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert

/**
 * The implementation of the [Apps] interface.
 */
class AppsImpl(
    private val logger: UiTestLogger,
    private val context: Context,
    private val uiDevice: UiDevice,
    private val adbServer: AdbServer
) : Apps {

    companion object {
        const val LAUNCH_RECENT_TIMEOUT = 1_000L
        const val LAUNCH_APP_TIMEOUT = 5_000L
    }

    private val chromePackageName: String = "com.android.chrome"

    override val targetAppLauncherPackageName: String = uiDevice.launcherPackageName

    override val targetAppPackageName: String = context.packageName

    /**
     * Installs an app via ADB.
     *
     * Required Permissions: INTERNET.
     *
     * @param apkPath a path to an apk to be installed. The apk is hosted on the test server.
     */
    override fun install(apkPath: String) {
        adbServer.performAdb("install $apkPath")
    }

    /**
     * Installs an app via ADB only if [packageName] is not installed
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of the app to be checked.
     * @param apkPath a path to the apk to be installed. The apk is hosted on the test server.
     */
    override fun installIfNotExists(packageName: String, apkPath: String) {
        if (!isInstalled(packageName)) {
            install(apkPath)
        }
    }

    /**
     * Uninstalls an app via ADB.
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of an app to be deleted.
     */
    override fun uninstall(packageName: String) {
        adbServer.performAdb("uninstall $packageName")
    }

    /**
     * Uninstalls an app via ADB only if it installed
     *
     * Required Permissions: INTERNET.
     *
     * @param packageName an android package name of an app to be deleted.
     */
    override fun uninstallIfExists(packageName: String) {
        if (isInstalled(packageName)) {
            uninstall(packageName)
        }
    }

    /**
     * Checks app is installed on device
     *
     * @param packageName an android package name of the app to be checked.
     * @return a [Boolean] of installation state
     */
    override fun isInstalled(packageName: String): Boolean {
        val packageManager = context.packageManager
        if (packageManager == null) return false
        return try {
            packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun waitForLauncher(timeout: Long, launcherPackageName: String) {
        MatcherAssert.assertThat(
            uiDevice.launcherPackageName,
            CoreMatchers.notNullValue()
        )

        val condition = Until.hasObject(By.pkg(launcherPackageName).depth(0))

        Assert.assertTrue(
            uiDevice.wait(condition, timeout)
        )
    }

    override fun waitForAppLaunchAndReady(timeout: Long, packageName: String) {
        val condition = Until.hasObject(By.pkg(packageName).depth(0))

        Assert.assertTrue(
            uiDevice.wait(condition, timeout)
        )
    }

    /**
     * Opens the given [url] on Chrome.
     *
     * @param url the url to be opened.
     */
    override fun openUrlInChrome(url: String) = launch(chromePackageName, Uri.parse(url))

    /**
     * Launches an app with given [packageName].
     *
     * @param packageName the package name of an app to launch.
     * @param data the data to put to the launch intent.
     */
    override fun launch(packageName: String, data: Uri?) {
        val intent = context.packageManager
            ?.getLaunchIntentForPackage(packageName)
            ?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        intent ?: apply {
            logger.e("Can not build an intent to launch app for: $packageName")
            return
        }

        data?.let { intent!!.data = it }

        context.startActivity(intent)

        val condition = Until.hasObject(By.pkg(packageName).depth(0))

        uiDevice.wait(condition, LAUNCH_APP_TIMEOUT)
    }

    /**
     * Opens the app from the recent list by the description.
     *
     * @param contentDescription the description of the app to launch.
     */
    override fun openRecent(contentDescription: String) {
        uiDevice.pressRecentApps()

        val appSelector = UiSelector().descriptionContains(contentDescription)
        val recentApp = uiDevice.findObject(appSelector)

        Thread.sleep(LAUNCH_RECENT_TIMEOUT)

        if (recentApp.exists()) {
            recentApp.click()
        }

        Thread.sleep(LAUNCH_RECENT_TIMEOUT)
    }

    /**
     * Kills the process of the app by the given [packageName].
     *
     * @param packageName the package name of the app to be killed.
     */
    override fun kill(packageName: String) {
        Runtime.getRuntime().exec(arrayOf("am", "force-stop", packageName))
    }
}