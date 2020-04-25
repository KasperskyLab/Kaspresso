[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase](../index.md) / [TestRunWatcherInterceptor](./index.md)

# TestRunWatcherInterceptor

`interface TestRunWatcherInterceptor : `[`TestContextHolder`](../-test-context-holder/index.md)

The interface for all interceptors intercepting test run events.

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | `open fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md) | `open fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onAfterSectionStarted](on-after-section-started.md) | `open fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | `open fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md) | `open fun onBeforeSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onBeforeSectionStarted](on-before-section-started.md) | `open fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onMainSectionFinishedFailed](on-main-section-finished-failed.md) | `open fun onMainSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onMainSectionFinishedSuccess](on-main-section-finished-success.md) | `open fun onMainSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onMainSectionStarted](on-main-section-started.md) | `open fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
| [onTestFinished](on-test-finished.md) | `open fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: Boolean): Unit` |
| [onTestStarted](on-test-started.md) | `open fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |

### Inheritors

| Name | Summary |
|---|---|
| [BuildStepReportWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report/-build-step-report-watcher-interceptor/index.md) | `class BuildStepReportWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
| [DefaultTestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults/-default-test-run-watcher-interceptor/index.md) | `class DefaultTestRunWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
| [TestRunCompositeWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite/-test-run-composite-watcher-interceptor/index.md) | The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Composes all of [TestRunWatcherInterceptor](./index.md)s list into one composite [TestRunWatcherInterceptor](./index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event.`class TestRunCompositeWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
| [TestRunLoggerWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging/-test-run-logger-watcher-interceptor/index.md) | The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Logs [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) on each section event.`class TestRunLoggerWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
| [TestRunnerScreenshotWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot/-test-runner-screenshot-watcher-interceptor/index.md) | The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Takes screenshots if "before" or "after" sections failed.`class TestRunnerScreenshotWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
