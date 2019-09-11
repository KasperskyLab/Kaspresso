[kaspresso](../../index.md) / [com.kaspersky.kaspresso.systemsafety](../index.md) / [SystemDialogSafetyProviderImpl](index.md) / [passSystemDialogs](./pass-system-dialogs.md)

# passSystemDialogs

`fun <T> passSystemDialogs(action: () -> `[`T`](pass-system-dialogs.md#T)`): `[`T`](pass-system-dialogs.md#T)

Overrides [SystemDialogSafetyProvider.passSystemDialogs](../-system-dialog-safety-provider/pass-system-dialogs.md)

Invokes the given [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.passSystemDialogs.T)))/action) and hides the system dialog if the invocation is failed and the system
dialog is actually shown via [suppressSystemDialogs](#) call.

### Parameters

`action` - the action to invoke.

### Exceptions

`Throwable` - if caught while [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.passSystemDialogs.T)))/action) invocation error is not allowed
or if[suppressSystemDialogs](#) throws an exception.

**Return**
the result of [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProviderImpl.passSystemDialogs.T)))/action)'s invocation.

