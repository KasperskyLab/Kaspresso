[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [Apps](./index.md)

# Apps

`interface Apps`

The interface to work with installer, launcher and package manager.

### Properties

| Name | Summary |
|---|---|
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `abstract val targetAppLauncherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [targetAppPackageName](target-app-package-name.md) | `abstract val targetAppPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | `abstract fun install(apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Installs an app via ADB. |
| [installIfNotExist](install-if-not-exist.md) | `abstract fun installIfNotExist(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Installs an app via ADB only if [packageName](install-if-not-exist.md#com.kaspersky.kaspresso.device.apps.Apps$installIfNotExist(kotlin.String, kotlin.String)/packageName) is not installed |
| [isInstalled](is-installed.md) | `abstract fun isInstalled(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks app is installed on device |
| [kill](kill.md) | `abstract fun kill(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Kills the process of the app by the given [packageName](kill.md#com.kaspersky.kaspresso.device.apps.Apps$kill(kotlin.String)/packageName). |
| [launch](launch.md) | `abstract fun launch(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Launches an app with given [packageName](launch.md#com.kaspersky.kaspresso.device.apps.Apps$launch(kotlin.String, android.net.Uri)/packageName). |
| [openRecent](open-recent.md) | `abstract fun openRecent(contentDescription: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the app from the recent list by the description. |
| [openUrlInChrome](open-url-in-chrome.md) | `abstract fun openUrlInChrome(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the given [url](open-url-in-chrome.md#com.kaspersky.kaspresso.device.apps.Apps$openUrlInChrome(kotlin.String)/url) on Chrome. |
| [uninstall](uninstall.md) | `abstract fun uninstall(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Uninstalls an app via ADB. |
| [uninstallIfExist](uninstall-if-exist.md) | `abstract fun uninstallIfExist(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Uninstalls an app via ADB only if it installed |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `abstract fun waitForAppLaunchAndReady(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitForLauncher](wait-for-launcher.md) | `abstract fun waitForLauncher(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, launcherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppLauncherPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [AppsImpl](../-apps-impl/index.md) | `class AppsImpl : `[`Apps`](./index.md)<br>The implementation of the [Apps](./index.md) interface. |
