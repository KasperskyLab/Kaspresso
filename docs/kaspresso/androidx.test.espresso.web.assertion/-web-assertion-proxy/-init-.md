[kaspresso](../../index.md) / [androidx.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`WebAssertionProxy(webAssertion: WebAssertion<E>, matcher: Matcher<*>, watcherInterceptors: List<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>)`

A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls.

Uses [WebViewAssertions.ResultCheckingWebAssertion](#) class, that has package-local access in Espresso, so it has to be
in the same package.

