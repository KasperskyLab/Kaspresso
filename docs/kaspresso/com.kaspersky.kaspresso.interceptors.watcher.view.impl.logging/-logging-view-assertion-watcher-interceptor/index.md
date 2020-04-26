[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingViewAssertionWatcherInterceptor](./index.md)

# LoggingViewAssertionWatcherInterceptor

`class LoggingViewAssertionWatcherInterceptor : `[`ViewAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)

The implementation of [ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md) that logs info about [ViewAssertion](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [ViewAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md) that logs info about [ViewAssertion](#).`LoggingViewAssertionWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Writes info to [logger](#).`fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
