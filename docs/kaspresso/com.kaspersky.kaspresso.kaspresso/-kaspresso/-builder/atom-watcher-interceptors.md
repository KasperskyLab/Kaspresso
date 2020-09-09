[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [atomWatcherInterceptors](./atom-watcher-interceptors.md)

# atomWatcherInterceptors

`lateinit var atomWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`AtomWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)`>`

Holds the list of [AtomWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [AtomWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)s.
These interceptors are called by [com.kaspersky.kaspresso.proxy.AtomProxy](../../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)
before [androidx.test.espresso.web.model.Atom](#) is actually called.

