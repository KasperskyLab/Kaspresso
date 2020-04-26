[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [testRunWatcherInterceptors](./test-run-watcher-interceptors.md)

# testRunWatcherInterceptors

`lateinit var testRunWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`TestRunWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)`>`

Holds the list of [TestRunWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [TestRunWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s.
These interceptors are called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) in "run"
method on "test started", on all [com.kaspersky.kaspresso.testcases.core.sections](../../../com.kaspersky.kaspresso.testcases.core.sections/index.md)' "section started",
"section finished with success" and "section finished with failure", and "test finished" events.

