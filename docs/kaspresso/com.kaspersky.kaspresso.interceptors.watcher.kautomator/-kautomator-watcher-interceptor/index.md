[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md) / [KautomatorWatcherInterceptor](./index.md)

# KautomatorWatcherInterceptor

`interface KautomatorWatcherInterceptor<Interaction, Assertion, Action>`

The interface for all interceptors that are watching the default interaction in Kautomator.

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | Called to do some stuff before [UiInteraction.check](#) is actually called.`abstract fun interceptCheck(interaction: Interaction, assertion: Assertion): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [interceptPerform](intercept-perform.md) | Called to do some stuff before [UiInteraction.perform](#) is actually called.`abstract fun interceptPerform(interaction: Interaction, action: Action): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DeviceWatcherInterceptor](../-device-watcher-interceptor.md) | The derived from [KautomatorWatcherInterceptor](./index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior.`interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](./index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>` |
| [ObjectWatcherInterceptor](../-object-watcher-interceptor.md) | The derived from [KautomatorWatcherInterceptor](./index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior.`interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](./index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>` |
