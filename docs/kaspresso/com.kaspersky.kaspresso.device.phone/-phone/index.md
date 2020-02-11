[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.phone](../index.md) / [Phone](./index.md)

# Phone

`interface Phone`

The interface to work with telephony.

Required: Started AdbServer
    1. Download a file "kaspresso/artifacts/desktop.jar"
    2. Start AdbServer =&gt; input in cmd "java jar path_to_file/desktop.jar"
Methods demanding to use AdbServer in the default implementation of this interface are marked.
    But nobody can't deprecate you to write implementation that doesn't require AdbServer.

### Functions

| Name | Summary |
|---|---|
| [cancelCall](cancel-call.md) | `abstract fun cancelCall(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Cancels incoming call. |
| [emulateCall](emulate-call.md) | `abstract fun emulateCall(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Emulates incoming call. |
| [receiveSms](receive-sms.md) | `abstract fun receiveSms(number: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Emulates receiving an SMS from [number](receive-sms.md#com.kaspersky.kaspresso.device.phone.Phone$receiveSms(kotlin.String, kotlin.String)/number). |

### Inheritors

| Name | Summary |
|---|---|
| [PhoneImpl](../-phone-impl/index.md) | `class PhoneImpl : `[`Phone`](./index.md)<br>The implementation of the [Phone](./index.md) interface. |
