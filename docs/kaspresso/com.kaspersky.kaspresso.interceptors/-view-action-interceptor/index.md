[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [ViewActionInterceptor](./index.md)

# ViewActionInterceptor

`interface ViewActionInterceptor`

An interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewActionProxy](../../com.kaspersky.kaspresso.proxy/-view-action-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(viewAction: ViewAction, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [ViewAction.perform](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingViewActionInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.logging/-logging-view-action-interceptor/index.md) | `class LoggingViewActionInterceptor : `[`ViewActionInterceptor`](./index.md)<br>An implementation of [ViewActionInterceptor](./index.md) that logs info about [ViewAction](#). |
