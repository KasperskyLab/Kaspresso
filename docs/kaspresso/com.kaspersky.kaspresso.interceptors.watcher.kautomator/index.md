[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](./index.md)

## Package com.kaspersky.kaspresso.interceptors.watcher.kautomator

### Types

| Name | Summary |
|---|---|
| [DeviceWatcherInterceptor](-device-watcher-interceptor.md) | `interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`<br>The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and [UiDeviceInteraction.check](#) behavior. |
| [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) | `interface KautomatorWatcherInterceptor<Interaction, Assertion, Action>`<br>The interface for all interceptors that are watching the default interaction in Kautomator. |
| [ObjectWatcherInterceptor](-object-watcher-interceptor.md) | `interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`<br>The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and [UiObjectInteraction.check](#) behavior. |
