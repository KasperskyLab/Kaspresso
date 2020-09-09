[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](./index.md)

## Package com.kaspersky.kaspresso.interceptors.behaviorkautomator

### Types

| Name | Summary |
|---|---|
| [DeviceBehaviorInterceptor](-device-behavior-interceptor.md) | The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior.`interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>` |
| [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) | The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.`interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action>` |
| [ObjectBehaviorInterceptor](-object-behavior-interceptor.md) | The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior.`interface ObjectBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>` |
