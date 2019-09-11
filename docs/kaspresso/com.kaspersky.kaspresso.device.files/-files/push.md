[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](index.md) / [push](./push.md)

# push

`abstract fun push(serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Performs adb push.

Required Permissions: INTERNET.

### Parameters

`serverPath` - a file path relative to the server directory.

`devicePath` - a path to copy.