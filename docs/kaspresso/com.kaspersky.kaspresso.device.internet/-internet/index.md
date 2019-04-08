[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.internet](../index.md) / [Internet](./index.md)

# Internet

`interface Internet`

An interface to work with internet settings.

### Functions

| Name | Summary |
|---|---|
| [disable](disable.md) | `abstract fun disable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Disables wi-fi and mobile data using adb. |
| [enable](enable.md) | `abstract fun enable(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Enables wi-fi and mobile data using adb. |
| [toggleWiFi](toggle-wi-fi.md) | `abstract fun toggleWiFi(enable: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Toggles only wi-fi. Note: it works only if flight mode is off. |

### Inheritors

| Name | Summary |
|---|---|
| [InternetImpl](../-internet-impl/index.md) | `class InternetImpl : `[`Internet`](./index.md)<br>Default implementation of Internet interface. |
