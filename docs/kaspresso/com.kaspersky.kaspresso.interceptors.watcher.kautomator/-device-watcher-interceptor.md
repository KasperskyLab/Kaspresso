[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](index.md) / [DeviceWatcherInterceptor](./-device-watcher-interceptor.md)

# DeviceWatcherInterceptor

`interface DeviceWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>`

The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiDeviceInteraction.perform](#) and
[UiDeviceInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [LoggingDeviceWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-device-watcher-interceptor/index.md) | The implementation of [DeviceWatcherInterceptor](./-device-watcher-interceptor.md) that logs info about [UiDeviceAssertion](#) or [UiDeviceAction](#) and [UiDeviceInteraction](#) on which its activities are performing.`class LoggingDeviceWatcherInterceptor : `[`DeviceWatcherInterceptor`](./-device-watcher-interceptor.md) |
