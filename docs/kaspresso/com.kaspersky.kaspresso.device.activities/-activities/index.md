[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.activities](../index.md) / [Activities](./index.md)

# Activities

`interface Activities`

The interface to work with activities.

### Functions

| Name | Summary |
|---|---|
| [getResumed](get-resumed.md) | `abstract fun getResumed(): `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`?`<br>Finds and returns resumed activity if it exists. |
| [isCurrent](is-current.md) | `abstract fun isCurrent(clazz: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if passed activity is resumed. |

### Inheritors

| Name | Summary |
|---|---|
| [ActivitiesImpl](../-activities-impl/index.md) | `class ActivitiesImpl : `[`Activities`](./index.md)<br>The implementation of the [Activities](./index.md) interface. |
