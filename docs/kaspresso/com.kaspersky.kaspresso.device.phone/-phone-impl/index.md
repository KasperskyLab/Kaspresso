[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.phone](../index.md) / [PhoneImpl](./index.md)

# PhoneImpl

`class PhoneImpl : `[`Phone`](../-phone/index.md)

The implementation of the [Phone](../-phone/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PhoneImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [Phone](../-phone/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [cancelCall](cancel-call.md) | `fun cancelCall(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Cancels incoming call. |
| [emulateCall](emulate-call.md) | `fun emulateCall(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Emulates incoming call. |
| [receiveSms](receive-sms.md) | `fun receiveSms(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Emulates receiving an SMS from [number](receive-sms.md#com.kaspersky.kaspresso.device.phone.PhoneImpl$receiveSms(kotlin.String, kotlin.String)/number). |
