[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md) / [TestRunCompositeWatcherInterceptor](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`TestRunCompositeWatcherInterceptor(watcherInterceptors: List<`[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)`>, logger: `[`Logger`](../../com.kaspersky.kaspresso.logger/-logger/index.md)`, exceptions: MutableList<Throwable>)`

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface.
Composes all of [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s list into one composite [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

