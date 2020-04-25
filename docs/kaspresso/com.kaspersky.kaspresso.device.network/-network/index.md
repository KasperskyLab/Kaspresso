[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.network](../index.md) / [Network](./index.md)

# Network

`interface Network`

The interface to work with network settings.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | Disables wi-fi and mobile data using adb.`abstract fun disable(): Unit` |
| [enable](enable.md) | Enables wi-fi and mobile data using adb.`abstract fun enable(): Unit` |
| [toggleWiFi](toggle-wi-fi.md) | Toggles only wi-fi. Note: it works only if flight mode is off.`abstract fun toggleWiFi(enable: Boolean): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [NetworkImpl](../-network-impl/index.md) | The implementation of the [Network](./index.md) interface.`class NetworkImpl : `[`Network`](./index.md) |
