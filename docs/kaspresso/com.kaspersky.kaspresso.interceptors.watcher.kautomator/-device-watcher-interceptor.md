[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](index.md) / [DeviceWatcherInterceptor](./-device-watcher-interceptor.md)

# DeviceWatcherInterceptor

`interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`

The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and
[UiDeviceInteraction.check](#) behavior.

### Inherited Functions

| Name | Summary |
|---|---|
| [interceptCheck](-kautomator-watcher-interceptor/intercept-check.md) | `abstract fun interceptCheck(interaction: `[`Interaction`](-kautomator-watcher-interceptor/index.md#Interaction)`, assertion: `[`Assertion`](-kautomator-watcher-interceptor/index.md#Assertion)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.check](#) is actually called. |
| [interceptPerform](-kautomator-watcher-interceptor/intercept-perform.md) | `abstract fun interceptPerform(interaction: `[`Interaction`](-kautomator-watcher-interceptor/index.md#Interaction)`, action: `[`Action`](-kautomator-watcher-interceptor/index.md#Action)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.perform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingDeviceWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-device-watcher-interceptor/index.md) | `class LoggingDeviceWatcherInterceptor : `[`DeviceWatcherInterceptor`](./-device-watcher-interceptor.md)<br>The implementation of [DeviceWatcherInterceptor](./-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#) and [UiDeviceInteraction](#) on which its activities are performing. |
