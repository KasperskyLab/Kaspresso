[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [AtomWatcherInterceptor](./index.md)

# AtomWatcherInterceptor

`interface AtomWatcherInterceptor`

The interface for all atom interceptors, used in [com.kaspersky.kaspresso.proxy.AtomProxy](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Called to do some stuff before [androidx.test.espresso.web.model.Atom.transform](#) is actually called.`abstract fun intercept(atomProxy: `[`AtomProxy`](../../com.kaspersky.kaspresso.proxy/-atom-proxy/index.md)`<*>, evaluation: Evaluation?): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingAtomWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-atom-watcher-interceptor/index.md) | The implementation of [AtomWatcherInterceptor](./index.md) that logs info about web action.`class LoggingAtomWatcherInterceptor : `[`AtomWatcherInterceptor`](./index.md) |
