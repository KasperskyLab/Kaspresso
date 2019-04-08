[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors](../index.md) / [WebAssertionInterceptor](./index.md)

# WebAssertionInterceptor

`interface WebAssertionInterceptor`

An interface for all atom interceptors, used in [WebAssertionProxy](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../android.support.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [android.support.test.espresso.web.assertion.WebAssertion.checkResult](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingWebAssertionInterceptor](../../com.kaspersky.kaspresso.interceptors.impl.logging/-logging-web-assertion-interceptor/index.md) | `class LoggingWebAssertionInterceptor : `[`WebAssertionInterceptor`](./index.md)<br>An implementation of [WebAssertionInterceptor](./index.md) that logs info about [android.support.test.espresso.web.assertion.WebAssertion](#). |
