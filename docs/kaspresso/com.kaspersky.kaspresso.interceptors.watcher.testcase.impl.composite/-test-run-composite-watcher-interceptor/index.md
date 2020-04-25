[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md) / [TestRunCompositeWatcherInterceptor](./index.md)

# TestRunCompositeWatcherInterceptor

`class TestRunCompositeWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface.
Composes all of [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s list into one composite [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) that is actually
called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Composes all of [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)s list into one composite [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.`TestRunCompositeWatcherInterceptor(watcherInterceptors: List<`[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)`>, logger: `[`Logger`](../../com.kaspersky.kaspresso.logger/-logger/index.md)`, exceptions: MutableList<Throwable>)` |

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | Called on "after" section finishes with failure, delegates the interception to [watcherInterceptors](#).`fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md) | Called on "after" section finishes with success, delegates the interception to [watcherInterceptors](#).`fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onAfterSectionStarted](on-after-section-started.md) | Called on "after" section starts, delegates the interception to [watcherInterceptors](#).`fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | Called on "before" section finishes with failure, delegates the interception to [watcherInterceptors](#).`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md) | Called on "before" section finishes with success, delegates the interception to [watcherInterceptors](#).`fun onBeforeSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onBeforeSectionStarted](on-before-section-started.md) | Called on "before" section starts, delegates the interception to [watcherInterceptors](#).`fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onMainSectionFinishedFailed](on-main-section-finished-failed.md) | Called on "main" section finishes with failure, delegates the interception to [watcherInterceptors](#).`fun onMainSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onMainSectionFinishedSuccess](on-main-section-finished-success.md) | Called on "main" section finishes with success, delegates the interception to [watcherInterceptors](#).`fun onMainSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onMainSectionStarted](on-main-section-started.md) | Called on "main" section starts, delegates the interception to [watcherInterceptors](#).`fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onTestFinished](on-test-finished.md) | Called on the whole test finishes, delegates the interception to [watcherInterceptors](#).`fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: Boolean): Unit` |
| [onTestStarted](on-test-started.md) | Called on the whole test starts, delegates the interception to [watcherInterceptors](#).`fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [setBaseTestContext](set-base-test-context.md) | `fun setBaseTestContext(context: `[`BaseTestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-base-test-context.md)`): Unit` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [BIGGER](-b-i-g-g-e-r.md) | `const val BIGGER: Int` |
| [DOES_NOT_MATTER](-d-o-e-s_-n-o-t_-m-a-t-t-e-r.md) | `const val DOES_NOT_MATTER: Int` |
| [FEWER](-f-e-w-e-r.md) | `const val FEWER: Int` |
