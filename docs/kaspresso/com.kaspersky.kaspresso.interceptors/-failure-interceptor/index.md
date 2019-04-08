[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [FailureInterceptor](./index.md)

# FailureInterceptor

`interface FailureInterceptor`

An interface for all failure interceptors, called on failures.

### Functions

| Name | Summary |
|---|---|
| [interceptAndThrow](intercept-and-throw.md) | `abstract fun interceptAndThrow(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, viewMatcher: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Reference to this method is provided as [android.support.test.espresso.FailureHandler](#) and is called on failures, to do some stuff and throw an exception further. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingFailureInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.logging/-logging-failure-interceptor/index.md) | `class LoggingFailureInterceptor : `[`FailureInterceptor`](./index.md)<br>An implementation of [FailureInterceptor](./index.md) that logs rich description of failure. |
