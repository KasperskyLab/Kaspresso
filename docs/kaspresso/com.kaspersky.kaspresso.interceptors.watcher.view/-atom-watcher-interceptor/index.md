[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [AtomWatcherInterceptor](./index.md)

# AtomWatcherInterceptor

`interface AtomWatcherInterceptor`

The interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(atomProxy: `[`AtomProxy`](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)`<*>, evaluation: Evaluation?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [androidx.test.espresso.web.model.Atom.transform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingAtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-atom-watcher-interceptor/index.md) | `class LoggingAtomWatcherInterceptor : `[`AtomWatcherInterceptor`](./index.md)<br>The implementation of [AtomWatcherInterceptor](./index.md) that logs info about web action. |
