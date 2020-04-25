[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [HackPermissions](./index.md)

# HackPermissions

`interface HackPermissions`

The interface to grant any permissions (i.e. signature permissions) unfairly without any interaction with the user.

### Functions

| Name | Summary |
|---|---|
| [grant](grant.md) | `abstract fun grant(packageName: String, permission: String): Boolean` |

### Inheritors

| Name | Summary |
|---|---|
| [HackPermissionsImpl](../-hack-permissions-impl/index.md) | The implementation of the [HackPermissions](./index.md) interface.`class HackPermissionsImpl : `[`HackPermissions`](./index.md) |
