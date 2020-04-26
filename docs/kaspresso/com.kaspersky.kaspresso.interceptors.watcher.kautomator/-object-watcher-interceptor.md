[kaspresso](../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.kautomator](index.md) / [ObjectWatcherInterceptor](./-object-watcher-interceptor.md)

# ObjectWatcherInterceptor

`interface ObjectWatcherInterceptor : `[`KautomatorWatcherInterceptor`](-kautomator-watcher-interceptor/index.md)`<UiObjectInteraction, UiObjectAssertion, UiObjectAction>`

The derived from [KautomatorWatcherInterceptor](-kautomator-watcher-interceptor/index.md) interface for intercepting (only watching) [UiObjectInteraction.perform](#) and
[UiObjectInteraction.check](#) behavior.

### Inheritors

| Name | Summary |
|---|---|
| [LoggingObjectWatcherInterceptor](../com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging/-logging-object-watcher-interceptor/index.md) | The implementation of [ObjectWatcherInterceptor](./-object-watcher-interceptor.md) that logs info about [UiObjectAssertion](#) or [UiObjectAction](#) and [UiObjectInteraction](#) on which its activities are performing.`class LoggingObjectWatcherInterceptor : `[`ObjectWatcherInterceptor`](./-object-watcher-interceptor.md) |
