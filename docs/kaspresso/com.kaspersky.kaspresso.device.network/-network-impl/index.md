[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.network](../index.md) / [NetworkImpl](./index.md)

# NetworkImpl

`class NetworkImpl : `[`Network`](../-network/index.md)

The implementation of the [Network](../-network/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NetworkImpl(targetContext: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [Network](../-network/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | `fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disables wi-fi and mobile data using adb. |
| [enable](enable.md) | `fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Enables wi-fi and mobile data using adb. |
| [toggleWiFi](toggle-wi-fi.md) | `fun toggleWiFi(enable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Toggles only wi-fi. Note: it works only if flight mode is off. |
