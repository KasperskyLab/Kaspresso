[kaspresso](../../index.md) / [com.kaspersky.kaspresso.device.activities](../index.md) / [ActivitiesImpl](./index.md)

# ActivitiesImpl

`class ActivitiesImpl : `[`Activities`](../-activities/index.md)

The implementation of the [Activities](../-activities/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [Activities](../-activities/index.md) interface.`ActivitiesImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [assertCurrentActivity](assert-current-activity.md) | A form of [isCurrent](is-current.md) method for simplified usage.`fun <T : `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`> assertCurrentActivity(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getResumed](get-resumed.md) | Finds and returns resumed activity if it exists, otherwise logs error.`fun getResumed(): `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`?` |
| [isCurrent](is-current.md) | Checks if passed activity is resumed.`fun isCurrent(clazz: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<out `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
