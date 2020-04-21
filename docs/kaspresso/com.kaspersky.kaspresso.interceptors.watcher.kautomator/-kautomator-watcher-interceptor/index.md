[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](../index.md) / [KautomatorWatcherInterceptor](./index.md)

# KautomatorWatcherInterceptor

`interface KautomatorWatcherInterceptor<Interaction, Assertion, Action>`

The interface for all interceptors that are watching the default interaction in Kautomator.

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `abstract fun interceptCheck(interaction: `[`Interaction`](index.md#Interaction)`, assertion: `[`Assertion`](index.md#Assertion)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.check](#) is actually called. |
| [interceptPerform](intercept-perform.md) | `abstract fun interceptPerform(interaction: `[`Interaction`](index.md#Interaction)`, action: `[`Action`](index.md#Action)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [UiInteraction.perform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [DeviceWatcherInterceptor](../-device-watcher-interceptor.md) | `interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](./index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`<br>The derived from [KautomatorWatcherInterceptor](./index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior. |
| [ObjectWatcherInterceptor](../-object-watcher-interceptor.md) | `interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](./index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`<br>The derived from [KautomatorWatcherInterceptor](./index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior. |
