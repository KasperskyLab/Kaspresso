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
| [allowViaDialog](allow-via-dialog.md) | `abstract fun allowViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Passes the permission-requesting permissions dialog and allows permissions. |
| [clickOn](click-on.md) | `abstract fun clickOn(button: `[`Permissions.Button`](-button/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Passes the permission-requesting permissions dialog |
| [denyViaDialog](deny-via-dialog.md) | `abstract fun denyViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Passes the permission-requesting permissions dialog and denies permissions. |
| [isDialogVisible](is-dialog-visible.md) | `abstract fun isDialogVisible(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check the permission-requesting permissions dialog is visible. |

### Inheritors

| Name | Summary |
|---|---|
| [PermissionsImpl](../-permissions-impl/index.md) | `class PermissionsImpl : `[`Permissions`](./index.md)<br>The implementation of the [Permissions](./index.md) interface. |
