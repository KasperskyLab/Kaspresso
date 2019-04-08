[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [Apps](./index.md)

# Apps

`interface Apps`

An interface to work with installer, launcher and package manager.

### Properties

| Name | Summary |
|---|---|
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `abstract val targetAppLauncherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [targetAppPackageName](target-app-package-name.md) | `abstract val targetAppPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | `abstract fun install(apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Installs an app via ADB. |
| [kill](kill.md) | `abstract fun kill(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [launch](launch.md) | `abstract fun launch(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openRecent](open-recent.md) | `abstract fun openRecent(contentDescription: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openUrlInChrome](open-url-in-chrome.md) | `abstract fun openUrlInChrome(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [uninstall](uninstall.md) | `abstract fun uninstall(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Uninstalls an app via ADB. |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `abstract fun waitForAppLaunchAndReady(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitForLauncher](wait-for-launcher.md) | `abstract fun waitForLauncher(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = MAX_LAUNCH_TIME_MS, launcherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = targetAppLauncherPackageName): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [AppsImpl](../-apps-impl/index.md) | `class AppsImpl : `[`Apps`](./index.md)<br>Default implementation of Apps interface. |
