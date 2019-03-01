package com.kaspersky.uitest_framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Looper
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.uiautomator.*
import com.agoda.kakao.KView
import com.kaspersky.uitest_framework.viewactions.OrientationChangeAction
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert

object Device {

    private val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    private val rootElement: KView
        get() = KView { ViewMatchers.isRoot() }

//    val keyboard = KeyboardElement()

    fun rotate() = waitFor { OrientationChangeAction.toggle(getResumedActivity()) }

    fun handlePermissionReqiest(shouldAllowPermissions: Boolean) {
        try {
            val btnSelector = UiSelector()
                    .clickable(true)
                    .checkable(false)
                    .resourceId(
                            if (shouldAllowPermissions)
                                PERMISSION_ALLOW_BUTTON_ID
                            else
                                PERMISSION_DENY_BUTTON_ID
                    )

            val btn = find(btnSelector)

            if (btn.exists()) {
                btn.click()
            }
        } catch (e: UiObjectNotFoundException) {
//            Logger.e("There are no permissions dialog to interact with.")
        }
    }

    fun waitForLauncher(
            timeout: Long = Configuration.MAX_LAUNCH_TIME_MS,
            launcherPackageName: String = mDevice.launcherPackageName
    ) {
        assertThat(mDevice.launcherPackageName, CoreMatchers.notNullValue())

        assertTrue(wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)), timeout))
    }

    fun waitForAppLaunchAndReady(
            timeout: Long = Configuration.MAX_LAUNCH_TIME_MS,
            packageName: String = context.packageName
    ) {
        assertTrue(wait(Until.hasObject(By.pkg(packageName).depth(0)), timeout))
    }

    fun openUrlInChrome(url: String) {

        launchApp(CHROME_PACKAGE_NAME, Uri.parse(url))
    }

    fun launchApp(packageName: String, data: Uri? = null) {

        val context = context

        val intent = context.packageManager
                .getLaunchIntentForPackage(packageName)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        data?.let { intent.data = it }

        context.startActivity(intent)

        val condition = Until.hasObject(By.pkg(packageName).depth(0))

        Device.wait(condition, 5_000)
    }

    fun openRecentApp(contentDescription: String) {

        mDevice.pressRecentApps()

        val appSelector = UiSelector().descriptionContains(contentDescription)

        val recentApp = mDevice.findObject(appSelector)

        Thread.sleep(1_000)

        if (recentApp.exists()) {
            recentApp.click()
        }

        Thread.sleep(1_000)
    }

    fun killApp(packageName: String = context.packageName) {
        Runtime.getRuntime().exec(arrayOf("am", "force-stop", packageName))
    }

    inline fun <reified T : Activity> assertCurrentActivity() {

        UiThreadStatement.runOnUiThread {
            Assert.assertThat(getResumedActivity(), CoreMatchers.instanceOf(T::class.java))
        }
    }

    fun getResumedActivity(): Activity {

        var resumedActivity: Activity? = null

        val findResumedActivity = {
            val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED)

            if (resumedActivities.iterator().hasNext()) {
                resumedActivity = resumedActivities.iterator().next()
            }
        }

        if (isMainThread()) {
            findResumedActivity()
        } else {
            InstrumentationRegistry.getInstrumentation().runOnMainSync(findResumedActivity)
        }

        return resumedActivity ?: throw IllegalStateException("Resumed activity not found")
    }

    fun pressBack(failTestIfAppUnderTestClosed: Boolean = false) {

        if (failTestIfAppUnderTestClosed) {
            Espresso.pressBack()
        } else {
            Espresso.pressBackUnconditionally()
        }
    }

    fun pressHome(): Boolean = mDevice.pressHome()

    fun <T> wait(condition: SearchCondition<T>, timeout: Long): T = mDevice.wait(condition, timeout)

    fun find(selector: UiSelector): UiObject = mDevice.findObject(selector)

    fun findWithText(text: String): UiObject = find(UiSelector().text(text))

    fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

    fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}