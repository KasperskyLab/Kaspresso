[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](./index.md)

## Package com.kaspersky.kaspresso.interceptors.watcher.kautomator

### Types

| Name | Summary |
|---|---|
| [DeviceWatcherInterceptor](-device-watcher-interceptor.md) | The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior.`interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>` |
| [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) | The interface for all interceptors that are watching the default interaction in Kautomator.`interface KautomatorWatcherInterceptor<Interaction, Assertion, Action>` |
| [ObjectWatcherInterceptor](-object-watcher-interceptor.md) | The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior.`interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>` |
