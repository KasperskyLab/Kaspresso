[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [AppsImpl](./index.md)

# AppsImpl

`class AppsImpl : `[`Apps`](../-apps/index.md)

The implementation of the [Apps](../-apps/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AppsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [Apps](../-apps/index.md) interface. |

### Properties

| Name | Summary |
|---|---|
| [targetAppLauncherPackageName](target-app-launcher-package-name.md) | `val targetAppLauncherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [targetAppPackageName](target-app-package-name.md) | `val targetAppPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [install](install.md) | `fun install(apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Installs an app via ADB. |
| [installIfNotExists](install-if-not-exists.md) | `fun installIfNotExists(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Installs an app via ADB only if [packageName](install-if-not-exists.md#com.kaspersky.kaspresso.device.apps.AppsImpl$installIfNotExists(kotlin.String, kotlin.String)/packageName) is not installed |
| [isInstalled](is-installed.md) | `fun isInstalled(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks app is installed on device |
| [kill](kill.md) | `fun kill(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Kills the process of the app by the given [packageName](kill.md#com.kaspersky.kaspresso.device.apps.AppsImpl$kill(kotlin.String)/packageName). |
| [launch](launch.md) | `fun launch(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: `[`Uri`](https://developer.android.com/reference/android/net/Uri.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Launches an app with given [packageName](launch.md#com.kaspersky.kaspresso.device.apps.AppsImpl$launch(kotlin.String, android.net.Uri)/packageName). |
| [openRecent](open-recent.md) | `fun openRecent(contentDescription: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the app from the recent list by the description. |
| [openUrlInChrome](open-url-in-chrome.md) | `fun openUrlInChrome(url: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Opens the given [url](open-url-in-chrome.md#com.kaspersky.kaspresso.device.apps.AppsImpl$openUrlInChrome(kotlin.String)/url) on Chrome. |
| [uninstall](uninstall.md) | `fun uninstall(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Uninstalls an app via ADB. |
| [uninstallIfExists](uninstall-if-exists.md) | `fun uninstallIfExists(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Uninstalls an app via ADB only if it installed |
| [waitForAppLaunchAndReady](wait-for-app-launch-and-ready.md) | `fun waitForAppLaunchAndReady(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [waitForLauncher](wait-for-launcher.md) | `fun waitForLauncher(timeout: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, launcherPackageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [LAUNCH_APP_TIMEOUT](-l-a-u-n-c-h_-a-p-p_-t-i-m-e-o-u-t.md) | `const val LAUNCH_APP_TIMEOUT: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [LAUNCH_RECENT_TIMEOUT](-l-a-u-n-c-h_-r-e-c-e-n-t_-t-i-m-e-o-u-t.md) | `const val LAUNCH_RECENT_TIMEOUT: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
