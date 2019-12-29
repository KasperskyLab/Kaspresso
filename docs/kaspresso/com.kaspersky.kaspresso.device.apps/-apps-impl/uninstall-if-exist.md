[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.apps](../index.md) / [AppsImpl](index.md) / [uninstallIfExist](./uninstall-if-exist.md)

# uninstallIfExist

`fun uninstallIfExist(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [Apps.uninstallIfExist](../-apps/uninstall-if-exist.md)

Uninstalls an app via ADB only if it installed

Required Permissions: INTERNET.

### Parameters

`packageName` - an android package name of an app to be deleted.