[kaspresso](../../../index.md) / [com.kaspersky.kaspresso.kaspresso](../../index.md) / [Kaspresso](../index.md) / [Builder](index.md) / [stepWatcherInterceptors](./step-watcher-interceptors.md)

# stepWatcherInterceptors

`lateinit var stepWatcherInterceptors: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`StepWatcherInterceptor`](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)`>`

Holds the list of [StepWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)s.
If it was not specified, Kaspresso will use no [StepWatcherInterceptor](../../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)s.
These interceptors are called by [com.kaspersky.kaspresso.testcases.core.testcontext.TestContext](../../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md) in "step"
method on both "step started", "step finished with success", "step finished with failure" and
"step finally finished" events.

