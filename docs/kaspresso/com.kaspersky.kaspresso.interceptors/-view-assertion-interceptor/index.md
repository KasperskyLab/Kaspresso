[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [ViewAssertionInterceptor](./index.md)

# ViewAssertionInterceptor

`interface ViewAssertionInterceptor`

An interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewAssertionProxy](../../com.kaspersky.kaspresso.proxy/-view-assertion-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(viewAssertion: ViewAssertion, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, exception: NoMatchingViewException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [android.support.test.espresso.ViewAssertion.check](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingViewAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.logging/-logging-view-assertion-interceptor/index.md) | `class LoggingViewAssertionInterceptor : `[`ViewAssertionInterceptor`](./index.md)<br>An implementation of [ViewAssertionInterceptor](./index.md) that logs info about [ViewAssertion](#). |
