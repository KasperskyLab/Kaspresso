[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults](../index.md) / [DefaultTestRunWatcherInterceptor](./index.md)

# DefaultTestRunWatcherInterceptor

`class DefaultTestRunWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultTestRunWatcherInterceptor()` |

### Functions

| Name | Summary |
|---|---|
| [afterEachTest](after-each-test.md) | Set the action which will be executed after the test. The action has access to BaseTestContext. If you set @param override in false then the final beforeAction will be     beforeAction of the parent TestCase plus current @param action.     Otherwise final beforeAction will be only @param action.`fun afterEachTest(override: Boolean = false, action: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): Unit` |
| [beforeEachTest](before-each-test.md) | Set the action which will be executed before the test. The action has access to BaseTestContext. If you set @param override in false then the final beforeAction will be     beforeAction of the parent TestCase plus current @param action.     Otherwise final beforeAction will be only @param action.`fun beforeEachTest(override: Boolean = false, action: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`.() -> Unit): Unit` |
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | `fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md) | `fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onBeforeSectionStarted](on-before-section-started.md) | `fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [setBaseTestContext](set-base-test-context.md) | `fun setBaseTestContext(context: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`): Unit` |
