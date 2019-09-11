[kaspresso](../../index.md) / [com.kaspersky.kaspresso.systemsafety](../index.md) / [SystemDialogSafetyProvider](index.md) / [passSystemDialogs](./pass-system-dialogs.md)

# passSystemDialogs

`abstract fun <T> passSystemDialogs(action: () -> `[`T`](pass-system-dialogs.md#T)`): `[`T`](pass-system-dialogs.md#T)

Invokes the given [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider.passSystemDialogs.T)))/action) and hides the system dialog if the invocation is failed and the system
dialog is actually shown via [suppressSystemDialogs](#) call.

### Parameters

`action` - the action to invoke.

**Return**
the result of [action](pass-system-dialogs.md#com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider$passSystemDialogs(kotlin.Function0((com.kaspersky.kaspresso.systemsafety.SystemDialogSafetyProvider.passSystemDialogs.T)))/action)'s invocation.

