[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [TestRunLoggerWatcherInterceptor](./index.md)

# TestRunLoggerWatcherInterceptor

`class TestRunLoggerWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface.
Logs [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) on each section event.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Logs [TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) on each section event.`TestRunLoggerWatcherInterceptor(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | Logs the given [testInfo](on-after-section-finished-failed.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onAfterSectionFinishedFailed(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Throwable)/testInfo) on "after" section finishes with failure.`fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAfterSectionStarted](on-after-section-started.md) | Logs the given [testInfo](on-after-section-started.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onAfterSectionStarted(com.kaspersky.kaspresso.testcases.models.info.TestInfo)/testInfo) on "after" section starts.`fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | Logs the given [testInfo](on-before-section-finished-failed.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onBeforeSectionFinishedFailed(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Throwable)/testInfo) on "before" section finishes with failure.`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionStarted](on-before-section-started.md) | Logs the given [testInfo](on-before-section-started.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onBeforeSectionStarted(com.kaspersky.kaspresso.testcases.models.info.TestInfo)/testInfo) on "before" section starts.`fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionStarted](on-main-section-started.md) | Logs the given [testInfo](on-main-section-started.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onMainSectionStarted(com.kaspersky.kaspresso.testcases.models.info.TestInfo)/testInfo) on "main" section starts.`fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestFinished](on-test-finished.md) | Logs the given [testInfo](on-test-finished.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onTestFinished(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Boolean)/testInfo) on whole test finishes.`fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
