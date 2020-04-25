[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [Apps](./index.md)

# Apps

`interface Apps`

The interface to work with installer, launcher and package manager.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Properties

| Name | Summary |
|---|---|
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `abstract val targetAppLauncherPackageName: String` |
| [targetAppPackageName](target-app-package-name.md) | `abstract val targetAppPackageName: String` |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | Installs an app via ADB.`abstract fun install(apkPath: String): Unit` |
| [installIfNotExists](install-if-not-exists.md) | Installs an app via ADB only if [packageName](install-if-not-exists.md#com.kaspersky.kaspresso.device.apps.Apps$installIfNotExists(kotlin.String, kotlin.String)/packageName) is not installed`abstract fun installIfNotExists(packageName: String, apkPath: String): Unit` |
| [isInstalled](is-installed.md) | Checks app is installed on device`abstract fun isInstalled(packageName: String): Boolean` |
| [kill](kill.md) | Kills the process of the app by the given [packageName](kill.md#com.kaspersky.kaspresso.device.apps.Apps$kill(kotlin.String)/packageName).`abstract fun kill(packageName: String = targetAppPackageName): Unit` |
| [launch](launch.md) | Launches an app with given [packageName](launch.md#com.kaspersky.kaspresso.device.apps.Apps$launch(kotlin.String, android.net.Uri)/packageName).`abstract fun launch(packageName: String, data: Uri? = null): Unit` |
| [openRecent](open-recent.md) | Opens the app from the recent list by the description.`abstract fun openRecent(contentDescription: String): Unit` |
| [openUrlInChrome](open-url-in-chrome.md) | Opens the given [url](open-url-in-chrome.md#com.kaspersky.kaspresso.device.apps.Apps$openUrlInChrome(kotlin.String)/url) on Chrome.`abstract fun openUrlInChrome(url: String): Unit` |
| [uninstall](uninstall.md) | Uninstalls an app via ADB.`abstract fun uninstall(packageName: String): Unit` |
| [uninstallIfExists](uninstall-if-exists.md) | Uninstalls an app via ADB only if it installed`abstract fun uninstallIfExists(packageName: String): Unit` |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `abstract fun waitForAppLaunchAndReady(timeout: Long = MAX_LAUNCH_TIME_MS, packageName: String = targetAppPackageName): Unit` |
| [waitForLauncher](wait-for-launcher.md) | `abstract fun waitForLauncher(timeout: Long = MAX_LAUNCH_TIME_MS, launcherPackageName: String = targetAppLauncherPackageName): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [AppsImpl](../-apps-impl/index.md) | The implementation of the [Apps](./index.md) interface.`class AppsImpl : `[`Apps`](./index.md) |
