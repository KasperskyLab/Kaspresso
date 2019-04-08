[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingViewAssertionInterceptor](./index.md)

# LoggingViewAssertionInterceptor

`class LoggingViewAssertionInterceptor : `[`ViewAssertionInterceptor`](../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/index.md)

An implementation of [ViewAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/index.md) that logs info about [ViewAssertion](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingViewAssertionInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)`)`<br>An implementation of [ViewAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors/-view-assertion-interceptor/index.md) that logs info about [ViewAssertion](#). |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
