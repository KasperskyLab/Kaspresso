[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](index.md) / [ObjectWatcherInterceptor](./-object-watcher-interceptor.md)

# ObjectWatcherInterceptor

`interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`

The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and
[UiObjectInteraction.check](#) behavior.

### Inherited Functions

| Name | Summary |
|---|---|
| [interceptCheck](-kautomator-watcher-interceptor/intercept-check.md) | `abstract fun interceptCheck(interaction: `[`Interaction`](-kautomator-watcher-interceptor/index.md#Interaction)`, assertion: `[`Assertion`](-kautomator-watcher-interceptor/index.md#Assertion)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.check](#) is actually called. |
| [interceptPerform](-kautomator-watcher-interceptor/intercept-perform.md) | `abstract fun interceptPerform(interaction: `[`Interaction`](-kautomator-watcher-interceptor/index.md#Interaction)`, action: `[`Action`](-kautomator-watcher-interceptor/index.md#Action)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.perform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingObjectWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-object-watcher-interceptor/index.md) | `class LoggingObjectWatcherInterceptor : `[`ObjectWatcherInterceptor`](./-object-watcher-interceptor.md)<br>The implementation of [ObjectWatcherInterceptor](./-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#) and [UiObjectInteraction](#) on which its activities are performing. |
