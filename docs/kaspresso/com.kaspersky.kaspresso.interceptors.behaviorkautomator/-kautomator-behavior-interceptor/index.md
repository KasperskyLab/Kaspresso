[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](../index.md) / [KautomatorBehaviorInterceptor](./index.md)

# KautomatorBehaviorInterceptor

`interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action>`

The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.

### Functions

| Name | Summary |
|---|---|
| [interceptCheck](intercept-check.md) | Called to do some stuff and actually check an interaction with element.`abstract fun <T> interceptCheck(interaction: Interaction, assertion: Assertion, activity: () -> T): T` |
| [interceptPerform](intercept-perform.md) | Called to do some stuff and actually perform an interaction with element.`abstract fun <T> interceptPerform(interaction: Interaction, action: Action, activity: () -> T): T` |

### Inheritors

| Name | Summary |
|---|---|
| [DeviceBehaviorInterceptor](../-device-behavior-interceptor.md) | The derived from [KautomatorBehaviorInterceptor](./index.md) interface for intercepting [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior.`interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](./index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>` |
| [ObjectBehaviorInterceptor](../-object-behavior-interceptor.md) | The derived from [KautomatorBehaviorInterceptor](./index.md) interface for intercepting [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior.`interface ObjectBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](./index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>` |
