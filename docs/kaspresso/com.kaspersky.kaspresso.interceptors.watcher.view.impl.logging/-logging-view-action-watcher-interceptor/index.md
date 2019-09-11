[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingViewActionWatcherInterceptor](./index.md)

# LoggingViewActionWatcherInterceptor

`class LoggingViewActionWatcherInterceptor : `[`ViewActionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)

The implementation of [ViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md) that logs info about [ViewAction](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingViewActionWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`<br>The implementation of [ViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md) that logs info about [ViewAction](#). |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
