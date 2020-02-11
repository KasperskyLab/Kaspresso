[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [viewAssertionWatcherInterceptors](./view-assertion-watcher-interceptors.md)

# viewAssertionWatcherInterceptors

`lateinit var viewAssertionWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`ViewAssertionWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)`>`

Holds the list of [ViewAssertionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [ViewAssertionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)s.
These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewAssertionProxy](../../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md)
before actual [androidx.test.espresso.ViewAssertion.check](#) call.

