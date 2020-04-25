[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [PermissionsImpl](./index.md)

# PermissionsImpl

`class PermissionsImpl : `[`Permissions`](../-permissions/index.md)

The implementation of the [Permissions](../-permissions/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Permissions](../-permissions/index.md) interface.`PermissionsImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, uiDevice: UiDevice)` |

### Functions

| Name | Summary |
|---|---|
| [allowViaDialog](allow-via-dialog.md) | Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.`fun allowViaDialog(): Unit` |
| [clickOn](click-on.md) | Waits for 1 sec, passes the permission-requesting permissions dialog`fun clickOn(button: Button): Unit` |
| [denyViaDialog](deny-via-dialog.md) | Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.`fun denyViaDialog(): Unit` |
| [isDialogVisible](is-dialog-visible.md) | Waits for 1 sec, check the permission-requesting permissions dialog is visible.`fun isDialogVisible(): Boolean` |
