package com.kaspersky.kaspresso.kaspresso

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.kaspersky.adbserver.common.log.logger.LogLevel
import com.kaspersky.kaspresso.device.uideviceconfig.InstrumentationUiDeviceConfig
import com.kaspersky.kaspresso.device.uideviceconfig.UiDeviceConfig
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.accessibility.AccessibilityImpl
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.activities.ActivitiesImpl
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.apps.AppsImpl
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.exploit.ExploitImpl
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.files.FilesImpl
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.keyboard.KeyboardImpl
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.languages.LanguageImpl
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.location.LocationImpl
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.logcat.LogcatImpl
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.network.NetworkImpl
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.HackPermissionsImpl
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.permissions.PermissionsImpl
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.phone.PhoneImpl
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.CombinedScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerImpl
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.params.FlakySafetyParams

class KautomatorInstrumentationConfig : KautomatorConfig {

    override val canRunOnJvm: Boolean
        get() = false

    override fun getUiDeviceConfig(): UiDeviceConfig = InstrumentationUiDeviceConfig()

    override fun getAdbServer(libLogger: UiTestLogger): AdbServer =
        AdbServerImpl(LogLevel.WARN, libLogger)

    override fun getPermissions(libLogger: UiTestLogger): Permissions =
        PermissionsImpl(libLogger, uiDevice)

    override fun getNetwork(libLogger: UiTestLogger, adbServer: AdbServer): Network =
        NetworkImpl(getInstrumentation().targetContext, adbServer, libLogger)

    override fun getApps(libLogger: UiTestLogger, adbServer: AdbServer): Apps =
        AppsImpl(libLogger, getInstrumentation().context, uiDevice, adbServer)

    override fun getFiles(adbServer: AdbServer): Files = FilesImpl(adbServer)

    override fun getActivities(libLogger: UiTestLogger): Activities = ActivitiesImpl(libLogger)

    override fun getPhone(adbServer: AdbServer): Phone = PhoneImpl(adbServer)

    override fun getLocation(adbServer: AdbServer): Location = LocationImpl(adbServer)

    override fun getKeyboard(adbServer: AdbServer): Keyboard = KeyboardImpl(adbServer)

    override fun getScreenshots(libLogger: UiTestLogger, activities: Activities): Screenshots =
        ScreenshotsImpl(
            libLogger,
            CombinedScreenshotMaker(InternalScreenshotMaker(activities), ExternalScreenshotMaker()),
            DefaultScreenshotDirectoryProvider(groupByRunNumbers = true),
            DefaultScreenshotNameProvider(addTimestamps = false)
        )

    override fun getAccessibility(): Accessibility = AccessibilityImpl()

    override fun getHackPermissions(libLogger: UiTestLogger): HackPermissions =
        HackPermissionsImpl(getInstrumentation().uiAutomation, libLogger)

    override fun getExploit(adbServer: AdbServer, activities: Activities): Exploit =
        ExploitImpl(activities, uiDevice, adbServer)

    override fun getLanguage(libLogger: UiTestLogger): Language =
        LanguageImpl(libLogger, getInstrumentation().targetContext)

    override fun getLogcat(adbServer: AdbServer): Logcat = LogcatImpl(adbServer)

    override fun getObjectBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<ObjectBehaviorInterceptor> =
        mutableListOf(
            AutoScrollObjectBehaviorInterceptor(libLogger, autoScrollParams),
            UiObjectLoaderBehaviorInterceptor(libLogger),
            SystemDialogSafetyObjectBehaviorInterceptor(libLogger, uiDevice, adbServer),
            FlakySafeObjectBehaviorInterceptor(flakySafetyParams, libLogger)
        )

    override fun getDeviceBehaviourInterceptors(libLogger: UiTestLogger, adbServer: AdbServer, flakySafetyParams: FlakySafetyParams): MutableList<DeviceBehaviorInterceptor> =
        mutableListOf(
            SystemDialogSafetyDeviceBehaviorInterceptor(libLogger, uiDevice, adbServer),
            FlakySafeDeviceBehaviorInterceptor(flakySafetyParams, libLogger)
        )


    override fun getViewBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<ViewBehaviorInterceptor> =
        mutableListOf(
            AutoScrollViewBehaviorInterceptor(autoScrollParams, libLogger),
            SystemDialogSafetyViewBehaviorInterceptor(libLogger, uiDevice, adbServer),
            FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
        )

    override fun getDataBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<DataBehaviorInterceptor> = mutableListOf(
        SystemDialogSafetyDataBehaviorInterceptor(libLogger, uiDevice, adbServer),
        FlakySafeDataBehaviorInterceptor(flakySafetyParams, libLogger)
    )


    override fun getWebBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<WebBehaviorInterceptor> =
        mutableListOf(
            AutoScrollWebBehaviorInterceptor(autoScrollParams, libLogger),
            SystemDialogSafetyWebBehaviorInterceptor(libLogger, uiDevice, adbServer),
            FlakySafeWebBehaviorInterceptor(flakySafetyParams, libLogger)
        )

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
}

