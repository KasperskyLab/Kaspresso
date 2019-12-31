[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [Apps](index.md) / [installIfNotExist](./install-if-not-exist.md)

# installIfNotExist

`abstract fun installIfNotExist(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, apkPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Installs an app via ADB only if [packageName](install-if-not-exist.md#com.kaspersky.kaspresso.device.apps.Apps$installIfNotExist(kotlin.String, kotlin.String)/packageName) is not installed

Required Permissions: INTERNET.

### Parameters

`packageName` - an android package name of the app to be checked.

`apkPath` - a path to the apk to be installed. The apk is hosted on the test server.