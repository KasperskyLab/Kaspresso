package com.kaspersky.kaspresso.device

import android.annotation.TargetApi
import android.app.UiAutomation
import android.content.Context
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.*
import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.espressoext.viewactions.OrientationChangeAction
import com.kaspersky.kaspresso.device.activities.ActivitiesManager
import com.kaspersky.kaspresso.device.files.FilesManager
import com.kaspersky.kaspresso.device.internet.InternetManager
import com.kaspersky.kaspresso.device.apps.AppsManager
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsManager
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.getStackTraceAsString
import com.kaspersky.klkakao.configurator.KakaoConfigurator
import com.kaspersky.klkakao.delegates.ViewInteractionDelegate

object Device {

    private const val PACKAGE_INSTALLER_PACKAGE_NAME = "com.android.packageinstaller"
    private const val PERMISSION_DENY_BUTTON_ID = "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_deny_button"
    private const val PERMISSION_ALLOW_BUTTON_ID = "$PACKAGE_INSTALLER_PACKAGE_NAME:id/permission_allow_button"

    private val interactionDelegate: ViewInteractionDelegate
        get() {
            return KakaoConfigurator.createViewInteractionDelegate(
                Espresso.onView(ViewMatchers.isRoot())
            )
        }

    private val logger: UiTestLogger = Configurator.logger

    val context: Context
        get() = InstrumentationRegistry.getInstrumentation().context

    val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    val appsManager: AppsManager = Configurator.appsManager
    val activitiesManager: ActivitiesManager = Configurator.activitiesManager
    var filesManager: FilesManager = Configurator.filesManager
    var internetManager: InternetManager = Configurator.internetManager
    var screenshotsManager: ScreenshotsManager = Configurator.screenshotsManager

    fun rotate() {
        val resumedActivity = activitiesManager.getResumedActivity() ?: return

        interactionDelegate.perform(
            OrientationChangeAction.toggle(resumedActivity)
        )
    }

    /**
     * Available since api 24
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
     * Available since api 24
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

    fun pressBack(failTestIfAppUnderTestClosed: Boolean = false) {
        if (failTestIfAppUnderTestClosed) {
            Espresso.pressBack()
        } else {
            Espresso.pressBackUnconditionally()
        }
    }

    fun pressHome(): Boolean = uiDevice.pressHome()

    fun isSdkVersionHigherThan(version: Int): Boolean = Build.VERSION.SDK_INT >= version
}