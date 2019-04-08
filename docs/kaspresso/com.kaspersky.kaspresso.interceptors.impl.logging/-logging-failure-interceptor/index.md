[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingFailureInterceptor](./index.md)

# LoggingFailureInterceptor

`class LoggingFailureInterceptor : `[`FailureInterceptor`](../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/index.md)

An implementation of [FailureInterceptor](../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/index.md) that logs rich description of failure.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingFailureInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)`)`<br>An implementation of [FailureInterceptor](../../com.kaspersky.kaspresso.interceptors/-failure-interceptor/index.md) that logs rich description of failure. |

### Functions

| Name | Summary |
|---|---|
| [interceptAndThrow](intercept-and-throw.md) | `fun interceptAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#) and throws an exception further. |
