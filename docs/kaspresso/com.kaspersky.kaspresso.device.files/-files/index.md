[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](./index.md)

# Files

`interface Files`

The interface to work with file permissions.

### Functions

| Name | Summary |
|---|---|
| [push](push.md) | `abstract fun push(serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb push. |
| [remove](remove.md) | `abstract fun remove(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a file by given path. |

### Inheritors

| Name | Summary |
|---|---|
| [FilesImpl](../-files-impl/index.md) | `class FilesImpl : `[`Files`](./index.md)<br>The implementation of the [Files](./index.md) interface. |
