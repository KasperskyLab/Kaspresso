[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [ViewActionWatcherInterceptor](./index.md)

# ViewActionWatcherInterceptor

`interface ViewActionWatcherInterceptor`

The interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Called to do some stuff before [ViewAction.perform](#) is actually called.`abstract fun intercept(viewAction: ViewAction, view: View): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingViewActionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-view-action-watcher-interceptor/index.md) | The implementation of [ViewActionWatcherInterceptor](./index.md) that logs info about [ViewAction](#).`class LoggingViewActionWatcherInterceptor : `[`ViewActionWatcherInterceptor`](./index.md) |
