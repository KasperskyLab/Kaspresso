[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.phone](../index.md) / [PhoneImpl](./index.md)

# PhoneImpl

`class PhoneImpl : `[`Phone`](../-phone/index.md)

The implementation of the [Phone](../-phone/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Phone](../-phone/index.md) interface.`PhoneImpl(adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [cancelCall](cancel-call.md) | Cancels incoming call.`fun cancelCall(number: String): Unit` |
| [emulateCall](emulate-call.md) | Emulates incoming call.`fun emulateCall(number: String): Unit` |
| [receiveSms](receive-sms.md) | Emulates receiving an SMS from [number](receive-sms.md#com.kaspersky.kaspresso.device.phone.PhoneImpl$receiveSms(kotlin.String, kotlin.String)/number).`fun receiveSms(number: String, text: String): Unit` |
