[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view](../index.md) / [WebAssertionWatcherInterceptor](./index.md)

# WebAssertionWatcherInterceptor

`interface WebAssertionWatcherInterceptor`

The interface for all atom interceptors, used in [WebAssertionProxy](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md).

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Called to do some stuff before [androidx.test.espresso.web.assertion.WebAssertion.checkResult](#) is actually called.`abstract fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: WebView?, result: Any): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [LoggingWebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging/-logging-web-assertion-watcher-interceptor/index.md) | The implementation of [WebAssertionWatcherInterceptor](./index.md) that logs info about [androidx.test.espresso.web.assertion.WebAssertion](#).`class LoggingWebAssertionWatcherInterceptor : `[`WebAssertionWatcherInterceptor`](./index.md) |
