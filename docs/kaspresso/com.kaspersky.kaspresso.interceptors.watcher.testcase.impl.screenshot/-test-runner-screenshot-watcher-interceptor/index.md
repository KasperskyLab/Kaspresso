[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [TestRunnerScreenshotWatcherInterceptor](./index.md)

# TestRunnerScreenshotWatcherInterceptor

`class TestRunnerScreenshotWatcherInterceptor : `[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)

The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface.
Takes screenshots if "before" or "after" sections failed.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TestRunnerScreenshotWatcherInterceptor(screenshots: `[`Screenshots`](../../com.kaspersky.kaspresso.device.screenshots/-screenshots/index.md)`)`<br>The implementation of the [TestRunWatcherInterceptor](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md) interface. Takes screenshots if "before" or "after" sections failed. |

### Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedFailed](on-after-section-finished-failed.md) | `fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes a screenshot of the screen on which the "after" section failed. |
| [onBeforeSectionFinishedFailed](on-before-section-finished-failed.md) | `fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Takes a screenshot of the screen on which the "before" section failed. |
| [onTestStarted](on-test-started.md) | `fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [onAfterSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-after-section-finished-success.md) | `open fun onAfterSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onAfterSectionStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-after-section-started.md) | `open fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-success.md) | `open fun onBeforeSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBeforeSectionStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-started.md) | `open fun onBeforeSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-failed.md) | `open fun onMainSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionFinishedSuccess](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-finished-success.md) | `open fun onMainSectionFinishedSuccess(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMainSectionStarted](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-main-section-started.md) | `open fun onMainSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTestFinished](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-finished.md) | `open fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
