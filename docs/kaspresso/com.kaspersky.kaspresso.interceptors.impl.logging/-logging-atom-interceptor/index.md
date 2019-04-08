[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.impl.logging](../index.md) / [LoggingAtomInterceptor](./index.md)

# LoggingAtomInterceptor

`class LoggingAtomInterceptor : `[`AtomInterceptor`](../../com.kaspersky.kaspresso.interceptors/-atom-interceptor/index.md)

An implementation of [AtomInterceptor](../../com.kaspersky.kaspresso.interceptors/-atom-interceptor/index.md) that logs info about web action.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LoggingAtomInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger/index.md)`)`<br>An implementation of [AtomInterceptor](../../com.kaspersky.kaspresso.interceptors/-atom-interceptor/index.md) that logs info about web action. |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `fun intercept(evaluation: Evaluation?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes info to [logger](#). |
