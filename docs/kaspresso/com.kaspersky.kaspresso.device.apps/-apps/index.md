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
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `abstract val targetAppLauncherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [targetAppPackageName](target-app-package-name.md) | `abstract val targetAppPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | Installs an app via ADB.`abstract fun install(apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [installIfNotExists](install-if-not-exists.md) | Installs an app via ADB only if [packageName](install-if-not-exists.md#com.kaspersky.kaspresso.device.apps.Apps$installIfNotExists(kotlin.String, kotlin.String)/packageName) is not installed`abstract fun installIfNotExists(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isInstalled](is-installed.md) | Checks app is installed on device`abstract fun isInstalled(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [kill](kill.md) | Kills the process of the app by the given [packageName](kill.md#com.kaspersky.kaspresso.device.apps.Apps$kill(kotlin.String)/packageName).`abstract fun kill(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [launch](launch.md) | Launches an app with given [packageName](launch.md#com.kaspersky.kaspresso.device.apps.Apps$launch(kotlin.String, android.net.Uri)/packageName).`abstract fun launch(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openRecent](open-recent.md) | Opens the app from the recent list by the description.`abstract fun openRecent(contentDescription: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openUrlInChrome](open-url-in-chrome.md) | Opens the given [url](open-url-in-chrome.md#com.kaspersky.kaspresso.device.apps.Apps$openUrlInChrome(kotlin.String)/url) on Chrome.`abstract fun openUrlInChrome(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [uninstall](uninstall.md) | Uninstalls an app via ADB.`abstract fun uninstall(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [uninstallIfExists](uninstall-if-exists.md) | Uninstalls an app via ADB only if it installed`abstract fun uninstallIfExists(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `abstract fun waitForAppLaunchAndReady(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitForLauncher](wait-for-launcher.md) | `abstract fun waitForLauncher(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, launcherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppLauncherPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [AppsImpl](../-apps-impl/index.md) | The implementation of the [Apps](./index.md) interface.`class AppsImpl : `[`Apps`](./index.md) |
