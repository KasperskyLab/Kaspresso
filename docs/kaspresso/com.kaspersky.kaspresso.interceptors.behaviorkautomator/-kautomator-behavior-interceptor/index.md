[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md) / [KautomatorBehaviorInterceptor](./index.md)

# KautomatorBehaviorInterceptor

`interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action>`

The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | `abstract fun <T> interceptCheck(interaction: `[`Interaction`](index.md#Interaction)`, assertion: `[`Assertion`](index.md#Assertion)`, activity: () -> `[`T`](intercept-check.md#T)`): `[`T`](intercept-check.md#T)<br>Called to do some stuff and actually check an interaction with element. |
| [interceptPerform](intercept-perform.md) | `abstract fun <T> interceptPerform(interaction: `[`Interaction`](index.md#Interaction)`, action: `[`Action`](index.md#Action)`, activity: () -> `[`T`](intercept-perform.md#T)`): `[`T`](intercept-perform.md#T)<br>Called to do some stuff and actually perform an interaction with element. |

### Inheritors

| Name | Summary |
|---|---|
| [DeviceBehaviorInterceptor](../-device-behavior-interceptor.md) | `interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](./index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`<br>The derived from [KautomatorBehaviorInterceptor](./index.md) interface for intercepting [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior. |
| [ObjectBehaviorInterceptor](../-object-behavior-interceptor.md) | `interface ObjectBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](./index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`<br>The derived from [KautomatorBehaviorInterceptor](./index.md) interface for intercepting [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior. |
