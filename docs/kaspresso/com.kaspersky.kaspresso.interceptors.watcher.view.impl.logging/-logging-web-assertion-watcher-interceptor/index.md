[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging](../index.md) / [LoggingWebAssertionWatcherInterceptor](./index.md)

# LoggingWebAssertionWatcherInterceptor

`class LoggingWebAssertionWatcherInterceptor : `[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)

The implementation of [WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md) that logs info about
[androidx.test.espresso.web.assertion.WebAssertion](#).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of [WebAssertionWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md) that logs info about [androidx.test.espresso.web.assertion.WebAssertion](#).`LoggingWebAssertionWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [intercept](intercept.md) | Writes info to [compositeLogger](#).`fun intercept(webAssertionProxy: `[`WebAssertionProxy`](../../androidx.test.espresso.web.assertion/-web-assertion-proxy/index.md)`<*>, view: WebView?, result: Any): Unit` |
