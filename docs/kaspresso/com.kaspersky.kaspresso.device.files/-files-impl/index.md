[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [FilesImpl](./index.md)

# FilesImpl

`class FilesImpl : `[`Files`](../-files/index.md)

The implementation of the [Files](../-files/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FilesImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [Files](../-files/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [pull](pull.md) | `fun pull(devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb pull. |
| [push](push.md) | `fun push(serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb push. |
| [remove](remove.md) | `fun remove(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a file by given path. |
