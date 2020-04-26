[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [Permissions](./index.md)

# Permissions

`interface Permissions`

The interface to work with permissions fairly by real permission dialogs.

### Types

| Name | Summary |
|---|---|
| [Button](-button/index.md) | `enum class Button` |

### Functions

| Name | Summary |
|---|---|
| [allowViaDialog](allow-via-dialog.md) | Passes the permission-requesting permissions dialog and allows permissions.`abstract fun allowViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clickOn](click-on.md) | Passes the permission-requesting permissions dialog`abstract fun clickOn(button: Button): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [denyViaDialog](deny-via-dialog.md) | Passes the permission-requesting permissions dialog and denies permissions.`abstract fun denyViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isDialogVisible](is-dialog-visible.md) | Check the permission-requesting permissions dialog is visible.`abstract fun isDialogVisible(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [PermissionsImpl](../-permissions-impl/index.md) | The implementation of the [Permissions](./index.md) interface.`class PermissionsImpl : `[`Permissions`](./index.md) |
