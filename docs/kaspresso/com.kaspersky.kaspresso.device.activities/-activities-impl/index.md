[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.activities](../index.md) / [ActivitiesImpl](./index.md)

# ActivitiesImpl

`class ActivitiesImpl : `[`Activities`](../-activities/index.md)

Default implementation of Activities interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ActivitiesImpl()`<br>Default implementation of Activities interface. |

### Functions

| Name | Summary |
|---|---|
| [assertCurrentActivity](assert-current-activity.md) | `fun <T : `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`> assertCurrentActivity(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>A form of [isCurrent](is-current.md) method for simplified usage. |
| [getResumed](get-resumed.md) | `fun getResumed(): `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`?`<br>Finds and returns resumed activity if it exists, otherwise logs error. |
| [isCurrent](is-current.md) | `fun isCurrent(clazz: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if passed activity is resumed. |
