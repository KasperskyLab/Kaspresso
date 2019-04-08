[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [AtomInterceptor](./index.md)

# AtomInterceptor

`interface AtomInterceptor`

An interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(evaluation: Evaluation?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [android.support.test.espresso.web.model.Atom.transform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingAtomInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.logging/-logging-atom-interceptor/index.md) | `class LoggingAtomInterceptor : `[`AtomInterceptor`](./index.md)<br>An implementation of [AtomInterceptor](./index.md) that logs info about web action. |
