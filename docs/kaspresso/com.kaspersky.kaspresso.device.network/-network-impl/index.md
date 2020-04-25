[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.network](../index.md) / [NetworkImpl](./index.md)

# NetworkImpl

`class NetworkImpl : `[`Network`](../-network/index.md)

The implementation of the [Network](../-network/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Network](../-network/index.md) interface.`NetworkImpl(targetContext: Context, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | Disables wi-fi and mobile data using adb.`fun disable(): Unit` |
| [enable](enable.md) | Enables wi-fi and mobile data using adb.`fun enable(): Unit` |
| [toggleWiFi](toggle-wi-fi.md) | Toggles only wi-fi. Note: it works only if flight mode is off.`fun toggleWiFi(enable: Boolean): Unit` |
