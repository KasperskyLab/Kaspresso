[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](index.md) / [pull](./pull.md)

# pull

`abstract fun pull(devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Performs adb pull.

Required Permissions: INTERNET.

### Parameters

`devicePath` - a file path relative to the device directory.

`serverPath` - a path to copy. (If empty - pulls in adbServer directory (folder with file "desktop.jar"))