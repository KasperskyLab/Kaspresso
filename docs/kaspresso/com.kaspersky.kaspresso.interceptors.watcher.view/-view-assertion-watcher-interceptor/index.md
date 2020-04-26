[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewAssertionWatcherInterceptor](./index.md)

# ViewAssertionWatcherInterceptor

`interface ViewAssertionWatcherInterceptor`

The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Called to do some stuff before [androidx.test.espresso.ViewAssertion.check](#) is actually called.`abstract fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-view-assertion-watcher-interceptor/index.md) | The implementation of [ViewAssertionWatcherInterceptor](./index.md) that logs info about [ViewAssertion](#).`class LoggingViewAssertionWatcherInterceptor : `[`ViewAssertionWatcherInterceptor`](./index.md) |
