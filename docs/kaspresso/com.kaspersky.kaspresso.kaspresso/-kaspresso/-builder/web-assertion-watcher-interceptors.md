[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [webAssertionWatcherInterceptors](./web-assertion-watcher-interceptors.md)

# webAssertionWatcherInterceptors

`lateinit var webAssertionWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`WebAssertionWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>`

Holds the list of [WebAssertionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [WebAssertionWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)s.
These interceptors are called by [androidx.test.espresso.web.assertion.WebAssertionProxy](../../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)
before actual [androidx.test.espresso.web.assertion.WebAssertion.checkResult](#) call.

