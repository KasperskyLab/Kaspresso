[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.internet](../index.md) / [InternetImpl](./index.md)

# InternetImpl

`class InternetImpl : `[`Internet`](../-internet/index.md)

Default implementation of Internet interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `InternetImpl()`<br>Default implementation of Internet interface. |

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | `fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disables wi-fi and mobile data using adb. |
| [enable](enable.md) | `fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Enables wi-fi and mobile data using adb. |
| [toggleWiFi](toggle-wi-fi.md) | `fun toggleWiFi(enable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Toggles only wi-fi. Note: it works only if flight mode is off. |
