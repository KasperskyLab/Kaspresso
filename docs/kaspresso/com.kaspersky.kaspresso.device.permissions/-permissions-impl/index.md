[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [PermissionsImpl](./index.md)

# PermissionsImpl

`class PermissionsImpl : `[`Permissions`](../-permissions/index.md)

The implementation of the [Permissions](../-permissions/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PermissionsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice)`<br>The implementation of the [Permissions](../-permissions/index.md) interface. |

### Functions

| Name | Summary |
|---|---|
| [allowViaDialog](allow-via-dialog.md) | `fun allowViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions. |
| [clickOn](click-on.md) | `fun clickOn(button: `[`Permissions.Button`](../-permissions/-button/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Waits for 1 sec, passes the permission-requesting permissions dialog |
| [denyViaDialog](deny-via-dialog.md) | `fun denyViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions. |
| [isDialogVisible](is-dialog-visible.md) | `fun isDialogVisible(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Waits for 1 sec, check the permission-requesting permissions dialog is visible. |
