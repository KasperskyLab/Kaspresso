[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase](../index.md) / [TestRunWatcherInterceptor](./index.md)

# TestRunWatcherInterceptor

`interface TestRunWatcherInterceptor`

The interface for all interceptors intercepting test run events.

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | `open fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAfterSectionFinishedSuccess](on-after-section-finished-success.md) | `open fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAfterSectionStarted](on-after-section-started.md) | `open fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | `open fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedSuccess](on-before-section-finished-success.md) | `open fun onBeforeSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionStarted](on-before-section-started.md) | `open fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedFailed](on-main-section-finished-failed.md) | `open fun onMainSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedSuccess](on-main-section-finished-success.md) | `open fun onMainSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionStarted](on-main-section-started.md) | `open fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestFinished](on-test-finished.md) | `open fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestStarted](on-test-started.md) | `open fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BuildStepReportWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report/-build-step-report-watcher-interceptor/index.md) | `class BuildStepReportWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md) |
| [TestRunCompositeWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite/-test-run-composite-watcher-interceptor/index.md) | `class TestRunCompositeWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md)<br>The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Composes all of [TestRunWatcherInterceptor](./index.md)s list into one composite [TestRunWatcherInterceptor](./index.md) that is actually called by [com.kaspersky.kaspresso.testcases.core.TestRunner](#) on each test event. |
| [TestRunLoggerWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging/-test-run-logger-watcher-interceptor/index.md) | `class TestRunLoggerWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md)<br>The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Logs [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) on each section event. |
| [TestRunnerScreenshotWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot/-test-runner-screenshot-watcher-interceptor/index.md) | `class TestRunnerScreenshotWatcherInterceptor : `[`TestRunWatcherInterceptor`](./index.md)<br>The implementation of the [TestRunWatcherInterceptor](./index.md) interface. Takes screenshots if "before" or "after" sections failed. |
