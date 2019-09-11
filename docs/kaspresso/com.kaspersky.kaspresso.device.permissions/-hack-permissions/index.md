[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.permissions](../index.md) / [HackPermissions](./index.md)

# HackPermissions

`interface HackPermissions`

The interface to grant any permissions (i.e. signature permissions) unfairly without any interaction with the user.

### Functions

| Name | Summary |
|---|---|
| [grant](grant.md) | `abstract fun grant(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, permission: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [HackPermissionsImpl](../-hack-permissions-impl/index.md) | `class HackPermissionsImpl : `[`HackPermissions`](./index.md)<br>The implementation of the [HackPermissions](./index.md) interface. |
