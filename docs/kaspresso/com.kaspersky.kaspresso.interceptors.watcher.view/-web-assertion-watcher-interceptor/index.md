[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [WebAssertionWatcherInterceptor](./index.md)

# WebAssertionWatcherInterceptor

`interface WebAssertionWatcherInterceptor`

The interface for all atom interceptors, used in [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | `abstract fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`?, result: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult](#) is actually called. |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingWebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-web-assertion-watcher-interceptor/index.md) | `class LoggingWebAssertionWatcherInterceptor : `[`WebAssertionWatcherInterceptor`](./index.md)<br>The implementation of [WebAssertionWatcherInterceptor](./index.md) that logs info about [androidx.test.espresso.web.assertion.WebAssertion](#). |
