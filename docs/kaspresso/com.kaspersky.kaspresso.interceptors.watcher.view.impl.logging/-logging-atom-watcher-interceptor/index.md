[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingAtomWatcherInterceptor](./index.md)

# LoggingAtomWatcherInterceptor

`class LoggingAtomWatcherInterceptor : `[`AtomWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)

The implementation of [AtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md) that logs info about web action.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingAtomWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [AtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md) that logs info about web action. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(atomProxy: `[`AtomProxy`](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)`<*>, evaluation: Evaluation?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
