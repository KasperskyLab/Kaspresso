package com.kaspersky.uitest_framework.device

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.UiAutomation
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.espressoext.viewactions.OrientationChangeAction
import com.agoda.kakao.configurator.KakaoConfigurator
import com.agoda.kakao.delegates.ViewInteractionDelegate
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.getStackTraceAsString

/**
 * A facade that encapsulates all the work outside the screens.
 */
object Device {

    private const val PACKAGE_INSTALLER_PACKAGE_NAME = "com.android.packageinstaller"

    private const val PERMISSION_DENY_BUTTON_ID =
        "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_deny_button"

    private const val PERMISSION_ALLOW_BUTTON_ID =
        "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_allow_button"

    val uiDevice: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val interactionDelegate: ViewInteractionDelegate
        get() {
            return KakaoConfigurator.createViewInteractionDelegate(
                Espresso.onView(ViewMatchers.isRoot())
            )
        }

    private val logger: UiTestLogger = Configurator.logger

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    val appsManager: AppsManager = AppsManager(uiDevice) { context }

    val activitiesManager: ActivitiesManager = ActivitiesManager()

    /**
     * Changes an orientation of device.
     */
    fun rotate() {
        val resumedActivity = activitiesManager.getResumedActivity() ?: return

        interactionDelegate.perform(
            OrientationChangeAction.toggle(resumedActivity)
        )
    }

    /**
     * Enables accessibility. Available since api 24.
     *
     * @param packageName a package name of an accessibility service
     * @param className a class name of an accessibility service
     */
    @TargetApi(Build.VERSION_CODES.N)
    fun enableAccessibility(
        packageName: String,
        className: String = "com.kaspersky.components.accessibility.KasperskyAccessibility"
    ) {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string $packageName/$className"

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }

    /**
     * Enables accessibility. Available since api 24.
     */
    @TargetApi(Build.VERSION_CODES.N)
    fun disableAccessibility() {
        val string = "enabled_accessibility_services"
        val cmd = "settings put secure $string ' '"

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation(UiAutomation.FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)
            .executeShellCommand(cmd)
            .close()
    }

    /**
     * Toggles WiFi state.
     *
     * @param enable if is set to true WiFi will be enabled, otherwise will be disabled.
     */
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

    /**
     * Passes the permission-requesting system dialog.
     *
     * @param shouldAllowPermissions if set to true permissions will be allowed, otherwise will not.
     */
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

            val btn = uiDevice.findObject(btnSelector)

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

    /**
     * Presses back button on the device.
     *
     * @param failTestIfAppUnderTestClosed if set to true, an exception will be thrown when Espresso navigates
     * outside the application or process under test.
     */
    fun pressBack(failTestIfAppUnderTestClosed: Boolean = false) {
        if (failTestIfAppUnderTestClosed) {
            Espresso.pressBack()
        } else {
            Espresso.pressBackUnconditionally()
        }
    }

    /**
     * Presses home button on the device.
     *
     * @return true if successful, else return false
     */
    fun pressHome(): Boolean = uiDevice.pressHome()

    /**
     * Wraps an SDK version checks.
     */
    fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}