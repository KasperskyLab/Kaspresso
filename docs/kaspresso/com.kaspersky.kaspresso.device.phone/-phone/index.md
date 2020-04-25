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
| [cancelCall](cancel-call.md) | Cancels incoming call.`abstract fun cancelCall(number: String): Unit` |
| [emulateCall](emulate-call.md) | Emulates incoming call.`abstract fun emulateCall(number: String): Unit` |
| [receiveSms](receive-sms.md) | Emulates receiving an SMS from [number](receive-sms.md#com.kaspersky.kaspresso.device.phone.Phone$receiveSms(kotlin.String, kotlin.String)/number).`abstract fun receiveSms(number: String, text: String): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [PhoneImpl](../-phone-impl/index.md) | The implementation of the [Phone](./index.md) interface.`class PhoneImpl : `[`Phone`](./index.md) |
