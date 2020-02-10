[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.behaviorkautomator](./index.md)

## Package com.kaspersky.kaspresso.interceptors.behaviorkautomator

### Types

| Name | Summary |
|---|---|
| [DeviceBehaviorInterceptor](-device-behavior-interceptor.md) | `interface DeviceBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`<br>The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior. |
| [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) | `interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action>`<br>The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls. |
| [ObjectBehaviorInterceptor](-object-behavior-interceptor.md) | `interface ObjectBehaviorInterceptor : `[`KautomatorBehaviorInterceptor`](-kautomator-behavior-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`<br>The derived from [KautomatorBehaviorInterceptor](-kautomator-behavior-interceptor/index.md) interface for intercepting [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior. |
