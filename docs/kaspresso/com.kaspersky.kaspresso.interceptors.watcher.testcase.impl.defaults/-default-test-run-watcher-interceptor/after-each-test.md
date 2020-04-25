[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults](../index.md) / [DefaultTestRunWatcherInterceptor](index.md) / [afterEachTest](./after-each-test.md)

# afterEachTest

`fun afterEachTest(override: Boolean = false, action: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): Unit`

Set the action which will be executed after the test.
The action has access to BaseTestContext.
If you set @param override in false then the final beforeAction will be
    beforeAction of the parent TestCase plus current @param action.
    Otherwise final beforeAction will be only @param action.

