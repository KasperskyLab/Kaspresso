[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [Files](./index.md)

# Files

`interface Files`

The interface to work with file permissions.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [pull](pull.md) | `abstract fun pull(devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb pull. |
| [push](push.md) | `abstract fun push(serverPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, devicePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Performs adb push. |
| [remove](remove.md) | `abstract fun remove(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Removes a file by given path. |

### Inheritors

| Name | Summary |
|---|---|
| [FilesImpl](../-files-impl/index.md) | `class FilesImpl : `[`Files`](./index.md)<br>The implementation of the [Files](./index.md) interface. |
