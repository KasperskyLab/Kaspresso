[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [deviceWatcherInterceptors](./device-watcher-interceptors.md)

# deviceWatcherInterceptors

`lateinit var deviceWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`DeviceWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)`>`

Holds the list of [DeviceWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)s.
If it was not specified, Kaspresso will use no [DeviceWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-device-watcher-interceptor.md)s.
These interceptors are called by [KautomatorDeviceInterceptor](#)
before actual [UiDeviceInteraction.check](#) and [UiDeviceInteraction.perform](#) call.

