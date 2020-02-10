[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [viewActionWatcherInterceptors](./view-action-watcher-interceptors.md)

# viewActionWatcherInterceptors

`lateinit var viewActionWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ViewActionWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)`>`

Holds the list of [ViewActionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [ViewActionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)s.
These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewActionProxy](../../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md)
before actual [androidx.test.espresso.ViewAction.perform](#) call.

