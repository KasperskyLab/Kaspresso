[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [TestRunLoggerWatcherInterceptor](index.md) / [onBeforeSectionFinishedFailed](./on-before-section-finished-failed.md)

# onBeforeSectionFinishedFailed

`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [TestRunWatcherInterceptor.onBeforeSectionFinishedFailed](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-before-section-finished-failed.md)

Logs the given [testInfo](on-before-section-finished-failed.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onBeforeSectionFinishedFailed(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Throwable)/testInfo) on "before" section finishes with failure.

### Parameters

`testInfo` - the test info to log.

`throwable` - the error occurred to log.