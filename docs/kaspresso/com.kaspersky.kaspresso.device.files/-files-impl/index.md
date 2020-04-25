[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.files](../index.md) / [FilesImpl](./index.md)

# FilesImpl

`class FilesImpl : `[`Files`](../-files/index.md)

The implementation of the [Files](../-files/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Files](../-files/index.md) interface.`FilesImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [pull](pull.md) | Performs adb pull.`fun pull(devicePath: String, serverPath: String): Unit` |
| [push](push.md) | Performs adb push.`fun push(serverPath: String, devicePath: String): Unit` |
| [remove](remove.md) | Removes a file by given path.`fun remove(path: String): Unit` |
