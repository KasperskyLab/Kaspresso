[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](./index.md)

# Files

`interface Files`

The interface to work with file permissions.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/adbserver-desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [pull](pull.md) | Performs adb pull.`abstract fun pull(devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [push](push.md) | Performs adb push.`abstract fun push(serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [remove](remove.md) | Removes a file by given path.`abstract fun remove(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [FilesImpl](../-files-impl/index.md) | The implementation of the [Files](./index.md) interface.`class FilesImpl : `[`Files`](./index.md) |
