package com.kaspersky.uitest_framework.device

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.UiAutomation
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.*
import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.espressoext.viewactions.OrientationChangeAction
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.getStackTraceAsString

object Device {

    private const val PACKAGE_INSTALLER_PACKAGE_NAME = "com.android.packageinstaller"

    private const val PERMISSION_DENY_BUTTON_ID =
            "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_deny_button"

    private const val PERMISSION_ALLOW_BUTTON_ID =
            "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_allow_button"

    private val uiDevice: UiDevice =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val interactionDelegate: ViewInteractionDelegate
        get() {
            return KakaoConfigurator.createViewInteractionDelegate(
                    Espresso.onView(ViewMatchers.isRoot())
            )
        }

    private val rootElement: KView
        get() = KView { ViewMatchers.isRoot() }

    private val logger: UiTestLogger = Configurator.logger

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    val appsManager: AppsManager = AppsManager(uiDevice) { context }

    val activitiesManager: ActivitiesManager = ActivitiesManager()

    fun rotate() {
        val resumedActivity = activitiesManager.getResumedActivity() ?: return

        interactionDelegate.perform(
                OrientationChangeAction.toggle(resumedActivity)
        )
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun enableAccessibility() {

        val packageName = "com.kms.free"
        val className = "com.kaspersky.components.accessibility.KasperskyAccessibility"
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string $packageName/$className"

        InstrumentationRegistry.getInstrumentation()
                .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
                .executeShellCommand(cmd)
                .close()

    }

    @SuppressLint("WifiManagerLeak")
    fun toggleWiFi(enable: Boolean) {

        val wifiManager =
                if (isSdkVersionHigherThan(Build.VERSION_CODES.N)) {
                    InstrumentationRegistry.getTargetContext()
                            .getSystemService(Context.WIFI_SERVICE) as WifiManager
                } else {
                    InstrumentationRegistry.getTargetContext()
                            .applicationContext
                            .getSystemService(Context.WIFI_SERVICE) as WifiManager
                }

        wifiManager.isWifiEnabled = enable
    }

    fun handlePermissionRequest(shouldAllowPermissions: Boolean) {
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
            logger.e("There are no permissions dialog to interact with.")
        } catch (e: Throwable) {
            logger.e(e.getStackTraceAsString())
            throw e
        }
    }

    fun pressBack(failTestIfAppUnderTestClosed: Boolean = false) {

        if (failTestIfAppUnderTestClosed) {
            Espresso.pressBack()
        } else {
            Espresso.pressBackUnconditionally()
        }
    }

    fun pressHome(): Boolean = uiDevice.pressHome()

    fun <T> wait(
            condition: SearchCondition<T>,
            timeout: Long
    ): T = uiDevice.wait(condition, timeout)

    fun find(selector: UiSelector): UiObject = uiDevice.findObject(selector)

    fun findWithText(text: String): UiObject = find(UiSelector().text(text))

    fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}