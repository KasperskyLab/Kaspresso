[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [TestRunnerScreenshotWatcherInterceptor](./index.md)

# TestRunnerScreenshotWatcherInterceptor

`class TestRunnerScreenshotWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface.
Takes screenshots if "before" or "after" sections failed.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Takes screenshots if "before" or "after" sections failed.`TestRunnerScreenshotWatcherInterceptor(screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | Takes a screenshot of the screen on which the "after" section failed.`fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | Takes a screenshot of the screen on which the "before" section failed.`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit` |
| [onTestStarted](on-test-started.md) | `fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit` |
