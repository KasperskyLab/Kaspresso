[kaspresso](../../index.md) / [com.kaspersky.kaspresso.systemsafety](../index.md) / [SystemDialogSafetyProviderImpl](./index.md)

# SystemDialogSafetyProviderImpl

`class SystemDialogSafetyProviderImpl : `[`SystemDialogSafetyProvider`](../-system-dialog-safety-provider/index.md)

The implementation of the [SystemDialogSafetyProvider](../-system-dialog-safety-provider/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SystemDialogSafetyProviderImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`)`<br>The implementation of the [SystemDialogSafetyProvider](../-system-dialog-safety-provider/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [passSystemDialogs](pass-system-dialogs.md) | `fun <T> passSystemDialogs(action: () -> `[`T`](pass-system-dialogs.md#T)`): `[`T`](pass-system-dialogs.md#T)<br>Invokes the given [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.passSystemDialogs.T)))/action) and hides the system dialog if the invocation is failed and the system dialog is actually shown via [suppressSystemDialogs](#) call. |
