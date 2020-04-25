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
| [assertCurrentActivity](assert-current-activity.md) | A form of [isCurrent](is-current.md) method for simplified usage.`fun <T : Activity> assertCurrentActivity(): Unit` |
| [getResumed](get-resumed.md) | Finds and returns resumed activity if it exists, otherwise logs error.`fun getResumed(): Activity?` |
| [isCurrent](is-current.md) | Checks if passed activity is resumed.`fun isCurrent(clazz: Class<out Activity>): Unit` |
