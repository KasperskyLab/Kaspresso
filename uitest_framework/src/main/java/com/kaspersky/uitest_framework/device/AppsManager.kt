package com.kaspersky.uitest_framework.device

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.Until
import com.kaspersky.uitest_framework.util.CHROME_PACKAGE_NAME
import com.kaspersky.uitest_framework.util.MAX_LAUNCH_TIME_MS
import junit.framework.Assert
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

class AppsManager(
    private val device: UiDevice,
    private val contextGetter: () -> Context
) {
    private val context: Context
        get() = contextGetter.invoke()

    fun waitForLauncher(
            timeout: Long = MAX_LAUNCH_TIME_MS,
            launcherPackageName: String = device.launcherPackageName
    ) {
        MatcherAssert.assertThat(
                device.launcherPackageName,
                CoreMatchers.notNullValue()
        )

        val condition = Until.hasObject(By.pkg(launcherPackageName).depth(0))

        Assert.assertTrue(
                device.wait(condition, timeout)
        )
    }

    fun waitForAppLaunchAndReady(
            timeout: Long = MAX_LAUNCH_TIME_MS,
            packageName: String = context.packageName
    ) {
        val condition = Until.hasObject(By.pkg(packageName).depth(0))

        Assert.assertTrue(
                device.wait(condition, timeout)
        )
    }

    fun openUrlInChrome(url: String) = launchApp(CHROME_PACKAGE_NAME, Uri.parse(url))

    fun launchApp(packageName: String, data: Uri? = null) {

        val intent = context.packageManager
                .getLaunchIntentForPackage(packageName)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        data?.let { intent.data = it }

        context.startActivity(intent)

        val condition = Until.hasObject(By.pkg(packageName).depth(0))

        device.wait(condition, 5_000)
    }

    fun openRecentApp(contentDescription: String) {

        device.pressRecentApps()

        val appSelector = UiSelector().descriptionContains(contentDescription)

        val recentApp = device.findObject(appSelector)

        Thread.sleep(1_000)

        if (recentApp.exists()) {
            recentApp.click()
        }

        Thread.sleep(1_000)
    }

    fun killApp(packageName: String = context.packageName) {

        Runtime.getRuntime().exec(arrayOf("am", "force-stop", packageName))
    }
}