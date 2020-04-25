[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.activities](../index.md) / [Activities](./index.md)

# Activities

`interface Activities`

The interface to work with activities.

### Functions

| Name | Summary |
|---|---|
| [getResumed](get-resumed.md) | Finds and returns resumed activity if it exists.`abstract fun getResumed(): Activity?` |
| [isCurrent](is-current.md) | Checks if passed activity is resumed.`abstract fun isCurrent(clazz: Class<out Activity>): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [ActivitiesImpl](../-activities-impl/index.md) | The implementation of the [Activities](./index.md) interface.`class ActivitiesImpl : `[`Activities`](./index.md) |
