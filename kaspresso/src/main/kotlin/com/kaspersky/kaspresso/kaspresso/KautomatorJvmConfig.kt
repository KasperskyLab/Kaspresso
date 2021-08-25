package com.kaspersky.kaspresso.kaspresso

import android.app.Activity
import android.net.Uri
import com.kaspersky.kaspresso.device.uideviceconfig.JvmUiDeviceConfig
import com.kaspersky.kaspresso.device.uideviceconfig.UiDeviceConfig
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.logcat.LogcatBufferSize
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.failure.exceptions.ActionNotSupportedInSharedTestException
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.params.FlakySafetyParams
import java.util.*

/**
 * Contains empty or default implementations of all those interfaces that require UiAutomator in order
 * to make it JVM compatible
 */
class KautomatorJvmConfig : KautomatorConfig {

    override val canRunOnJvm: Boolean
        get() = true

    override fun getUiDeviceConfig(): UiDeviceConfig = JvmUiDeviceConfig()

    override fun getAdbServer(libLogger: UiTestLogger): AdbServer = object : AdbServer {
        override fun performCmd(vararg commands: String): List<String> =
            throw ActionNotSupportedInSharedTestException("AdbServer")

        override fun performAdb(vararg commands: String): List<String> =
            throw ActionNotSupportedInSharedTestException("AdbServer")

        override fun performShell(vararg commands: String): List<String> =
            throw ActionNotSupportedInSharedTestException("AdbServer")

        override fun disconnectServer() {
            // no-op, this is called everywhere
        }
    }

    override fun getPermissions(libLogger: UiTestLogger): Permissions = object : Permissions {
        override fun allowViaDialog() {
            throw ActionNotSupportedInSharedTestException("Permissions")
        }

        override fun denyViaDialog() {
            throw ActionNotSupportedInSharedTestException("Permissions")
        }

        override fun isDialogVisible(): Boolean =
            throw ActionNotSupportedInSharedTestException("Permissions")

        override fun clickOn(button: Permissions.Button) {
            throw ActionNotSupportedInSharedTestException("Permissions")
        }
    }

    override fun getNetwork(libLogger: UiTestLogger, adbServer: AdbServer): Network = object : Network {
        override fun enable() {
            throw ActionNotSupportedInSharedTestException("Network")
        }

        override fun disable() {
            throw ActionNotSupportedInSharedTestException("Network")
        }

        override fun toggleMobileData(enable: Boolean) {
            throw ActionNotSupportedInSharedTestException("Network")
        }

        override fun toggleWiFi(enable: Boolean) {
            throw ActionNotSupportedInSharedTestException("Network")
        }
    }

    override fun getApps(libLogger: UiTestLogger, adbServer: AdbServer): Apps =
        object : Apps {
            override val targetAppLauncherPackageName: String
                get() = "JVM launcher package name"
            override val targetAppPackageName: String
                get() = "JVM package name"

            override fun install(apkPath: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun installIfNotExists(packageName: String, apkPath: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun uninstall(packageName: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun uninstallIfExists(packageName: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun isInstalled(packageName: String): Boolean =
                throw ActionNotSupportedInSharedTestException("Apps")

            override fun waitForLauncher(timeout: Long, launcherPackageName: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun waitForAppLaunchAndReady(timeout: Long, packageName: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun openUrlInChrome(url: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun launch(packageName: String, data: Uri?) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun openRecent(contentDescription: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }

            override fun kill(packageName: String) {
                throw ActionNotSupportedInSharedTestException("Apps")
            }
        }

    override fun getFiles(adbServer: AdbServer): Files = object : Files {
        override fun push(serverPath: String, devicePath: String) {
            throw ActionNotSupportedInSharedTestException("Files")
        }

        override fun remove(path: String) {
            throw ActionNotSupportedInSharedTestException("Files")
        }

        override fun pull(devicePath: String, serverPath: String) {
            throw ActionNotSupportedInSharedTestException("Files")
        }
    }

    override fun getActivities(libLogger: UiTestLogger): Activities = object : Activities {
        override fun getResumed(): Activity? =
            throw ActionNotSupportedInSharedTestException("Activities")

        override fun isCurrent(clazz: Class<out Activity>) {
            throw ActionNotSupportedInSharedTestException("Activities")
        }
    }

    override fun getPhone(adbServer: AdbServer): Phone = object : Phone {
        override fun emulateCall(number: String) {
            throw ActionNotSupportedInSharedTestException("Phone")
        }

        override fun cancelCall(number: String) {
            throw ActionNotSupportedInSharedTestException("Phone")
        }

        override fun receiveSms(number: String, text: String) {
            throw ActionNotSupportedInSharedTestException("Phone")
        }
    }

    override fun getLocation(adbServer: AdbServer): Location = object : Location {
        override fun enableGps() {
            throw ActionNotSupportedInSharedTestException("Location")
        }

        override fun disableGps() {
            throw ActionNotSupportedInSharedTestException("Location")
        }

        override fun setLocation(lat: Double, lon: Double) {
            throw ActionNotSupportedInSharedTestException("Location")
        }
    }

    override fun getKeyboard(adbServer: AdbServer): Keyboard = object : Keyboard {
        override fun typeText(text: String) {
            throw ActionNotSupportedInSharedTestException("Keyboard")
        }

        override fun sendEvent(keyEvent: Int) {
            throw ActionNotSupportedInSharedTestException("Keyboard")
        }
    }

    override fun getScreenshots(libLogger: UiTestLogger, activities: Activities): Screenshots = object : Screenshots {
        override fun take(tag: String) {
            throw ActionNotSupportedInSharedTestException("Screenshots")
        }
    }

    override fun getAccessibility(): Accessibility = object : Accessibility {
        override fun enable(packageName: String, className: String) {
            throw ActionNotSupportedInSharedTestException("Accessibility")
        }

        override fun disable() {
            throw ActionNotSupportedInSharedTestException("Accessibility")
        }
    }

    override fun getHackPermissions(libLogger: UiTestLogger): HackPermissions = object : HackPermissions {
        override fun grant(packageName: String, permission: String): Boolean =
            throw ActionNotSupportedInSharedTestException("HackPermissions")
    }

    override fun getExploit(adbServer: AdbServer, activities: Activities): Exploit = object : Exploit {
        override fun rotate() {
            throw ActionNotSupportedInSharedTestException("Exploit")
        }

        override fun setOrientation(deviceOrientation: Exploit.DeviceOrientation) {
            throw ActionNotSupportedInSharedTestException("Exploit")
        }

        override fun setAutoRotationEnabled(enabled: Boolean) {
            throw ActionNotSupportedInSharedTestException("Exploit")
        }

        override fun pressBack(failTestIfAppUnderTestClosed: Boolean) {
            throw ActionNotSupportedInSharedTestException("Exploit")
        }

        override fun pressHome(): Boolean =
            throw ActionNotSupportedInSharedTestException("Exploit")
    }

    override fun getLanguage(libLogger: UiTestLogger): Language = object : Language {
        override fun switchInApp(locale: Locale) {
            throw ActionNotSupportedInSharedTestException("Language")
        }
    }

    override fun getLogcat(adbServer: AdbServer): Logcat = object : Logcat {
        override fun disableChatty() {
            throw ActionNotSupportedInSharedTestException("Logcat")
        }

        override fun setBufferSize(size: LogcatBufferSize) {
            throw ActionNotSupportedInSharedTestException("Logcat")
        }

        override fun setDefaultBufferSize() {
            throw ActionNotSupportedInSharedTestException("Logcat")
        }

        override fun clear(buffer: Logcat.Buffer) {
            throw ActionNotSupportedInSharedTestException("Logcat")
        }

        override fun readLogcatRows(
            excludePattern: String?,
            excludePatternIsIgnoreCase: Boolean,
            includePattern: String?,
            includePatternIsIgnoreCase: Boolean,
            buffer: Logcat.Buffer,
            rowLimit: Int?
        ): List<String> = throw ActionNotSupportedInSharedTestException("Logcat")

        override fun readLogcatRows(
            excludePattern: String?,
            excludePatternIsIgnoreCase: Boolean,
            includePattern: String?,
            includePatternIsIgnoreCase: Boolean,
            buffer: Logcat.Buffer,
            rowLimit: Int?,
            readingBlock: (logcatRow: String) -> Boolean
        ): Boolean = throw ActionNotSupportedInSharedTestException("Logcat")
    }

    override fun getObjectBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<ObjectBehaviorInterceptor> = mutableListOf(
        AutoScrollObjectBehaviorInterceptor(libLogger, autoScrollParams),
        UiObjectLoaderBehaviorInterceptor(libLogger),
        FlakySafeObjectBehaviorInterceptor(flakySafetyParams, libLogger)
    )

    override fun getDeviceBehaviourInterceptors(libLogger: UiTestLogger, adbServer: AdbServer, flakySafetyParams: FlakySafetyParams): MutableList<DeviceBehaviorInterceptor> =
        mutableListOf(
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
            FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
        )

    override fun getDataBehaviourInterceptors(
        libLogger: UiTestLogger,
        adbServer: AdbServer,
        autoScrollParams: AutoScrollParams,
        flakySafetyParams: FlakySafetyParams
    ): MutableList<DataBehaviorInterceptor> = mutableListOf(
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
            FlakySafeWebBehaviorInterceptor(flakySafetyParams, libLogger)
        )
}
