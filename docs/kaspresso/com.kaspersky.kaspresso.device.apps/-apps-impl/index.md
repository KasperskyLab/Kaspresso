[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [AppsImpl](./index.md)

# AppsImpl

`class AppsImpl : `[`Apps`](../-apps/index.md)

The implementation of the [Apps](../-apps/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Apps](../-apps/index.md) interface.`AppsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, context: Context, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `val targetAppLauncherPackageName: String` |
| [targetAppPackageName](target-app-package-name.md) | `val targetAppPackageName: String` |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | Installs an app via ADB.`fun install(apkPath: String): Unit` |
| [installIfNotExists](install-if-not-exists.md) | Installs an app via ADB only if [packageName](install-if-not-exists.md#com.kaspersky.kaspresso.device.apps.AppsImpl$installIfNotExists(kotlin.String, kotlin.String)/packageName) is not installed`fun installIfNotExists(packageName: String, apkPath: String): Unit` |
| [isInstalled](is-installed.md) | Checks app is installed on device`fun isInstalled(packageName: String): Boolean` |
| [kill](kill.md) | Kills the process of the app by the given [packageName](kill.md#com.kaspersky.kaspresso.device.apps.AppsImpl$kill(kotlin.String)/packageName).`fun kill(packageName: String): Unit` |
| [launch](launch.md) | Launches an app with given [packageName](launch.md#com.kaspersky.kaspresso.device.apps.AppsImpl$launch(kotlin.String, android.net.Uri)/packageName).`fun launch(packageName: String, data: Uri?): Unit` |
| [openRecent](open-recent.md) | Opens the app from the recent list by the description.`fun openRecent(contentDescription: String): Unit` |
| [openUrlInChrome](open-url-in-chrome.md) | Opens the given [url](open-url-in-chrome.md#com.kaspersky.kaspresso.device.apps.AppsImpl$openUrlInChrome(kotlin.String)/url) on Chrome.`fun openUrlInChrome(url: String): Unit` |
| [uninstall](uninstall.md) | Uninstalls an app via ADB.`fun uninstall(packageName: String): Unit` |
| [uninstallIfExists](uninstall-if-exists.md) | Uninstalls an app via ADB only if it installed`fun uninstallIfExists(packageName: String): Unit` |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `fun waitForAppLaunchAndReady(timeout: Long, packageName: String): Unit` |
| [waitForLauncher](wait-for-launcher.md) | `fun waitForLauncher(timeout: Long, launcherPackageName: String): Unit` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [LAUNCH_APP_TIMEOUT](-l-a-u-n-c-h_-a-p-p_-t-i-m-e-o-u-t.md) | `const val LAUNCH_APP_TIMEOUT: Long` |
| [LAUNCH_RECENT_TIMEOUT](-l-a-u-n-c-h_-r-e-c-e-n-t_-t-i-m-e-o-u-t.md) | `const val LAUNCH_RECENT_TIMEOUT: Long` |
