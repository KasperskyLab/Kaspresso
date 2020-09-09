[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [objectWatcherInterceptors](./object-watcher-interceptors.md)

# objectWatcherInterceptors

`lateinit var objectWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ObjectWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)`>`

Holds the list of [ObjectWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)s.
If it was not specified, Kaspresso will use no [ObjectWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.kautomator/-object-watcher-interceptor.md)s.
These interceptors are called by [KautomatorObjectInterceptor](#)
before actual [UiObjectInteraction.check](#) and [UiObjectInteraction.perform](#) call.

