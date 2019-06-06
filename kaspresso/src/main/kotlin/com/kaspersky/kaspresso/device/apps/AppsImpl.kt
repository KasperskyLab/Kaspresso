package com.kaspersky.kaspresso.device.apps

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.Until
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert

/**
 * Default implementation of Apps interface.
 */
class AppsImpl(
    private val logger: UiTestLogger
) : Apps {

    private val chromePackageName = "com.android.chrome"

    private val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    override val targetAppLauncherPackageName: String = uiDevice.launcherPackageName

    override val targetAppPackageName: String = context.packageName

    /**
     *  Installs an app via ADB.
     *
     *  @param apkPath a path to an apk to be installed. The apk is hosted on the test server.
     */
    override fun install(apkPath: String) {
        AdbServer.performAdb("install $apkPath")
    }

    /**
     *  Uninstalls an app via ADB.
     *
     *  @param packageName an android package name of an app to be deleted.
     */
    override fun uninstall(packageName: String) {
        AdbServer.performAdb("uninstall $packageName")
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

    override fun openUrlInChrome(url: String) = launch(chromePackageName, Uri.parse(url))

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

        uiDevice.wait(condition, 5_000)
    }

    override fun openRecent(contentDescription: String) {
        uiDevice.pressRecentApps()

        val appSelector = UiSelector().descriptionContains(contentDescription)
        val recentApp = uiDevice.findObject(appSelector)

        Thread.sleep(1_000)

        if (recentApp.exists()) {
            recentApp.click()
        }

        Thread.sleep(1_000)
    }

    override fun kill(packageName: String) {
        Runtime.getRuntime().exec(arrayOf("am", "force-stop", packageName))
    }
}