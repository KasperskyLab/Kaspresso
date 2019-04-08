[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingViewActionInterceptor](./index.md)

# LoggingViewActionInterceptor

`class LoggingViewActionInterceptor : `[`ViewActionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/index.md)

An implementation of [ViewActionInterceptor](../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/index.md) that logs info about [ViewAction](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingViewActionInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)`)`<br>An implementation of [ViewActionInterceptor](../../com.kaspersky.kaspresso.interceptors/-view-action-interceptor/index.md) that logs info about [ViewAction](#). |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
