[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [Permissions](./index.md)

# Permissions

`interface Permissions`

An interface to work with permissions.

### Functions

| Name | Summary |
|---|---|
| [allowViaDialog](allow-via-dialog.md) | `abstract fun allowViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Passes the permission-requesting permissions dialog and allows permissions. |
| [denyViaDialog](deny-via-dialog.md) | `abstract fun denyViaDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Passes the permission-requesting permissions dialog and denies permissions. |

### Inheritors

| Name | Summary |
|---|---|
| [PermissionsImpl](../-permissions-impl/index.md) | `class PermissionsImpl : `[`Permissions`](./index.md)<br>An implementation of Permissions interface. |
