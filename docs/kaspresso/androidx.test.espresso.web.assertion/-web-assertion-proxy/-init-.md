[kaspresso](../../index.md) / [androidx.test.espresso.web.assertion](../index.md) / [WebAssertionProxy](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`WebAssertionProxy(webAssertion: WebAssertion<`[`E`](index.md#E)`>, matcher: Matcher<*>, watcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>)`

A proxy-wrapper of [WebAssertion](#) for [watcherInterceptors](#) calls.

Uses [WebViewAssertions.ResultCheckingWebAssertion](#) class, that has package-local access in Espresso, so it has to be
in the same package.

