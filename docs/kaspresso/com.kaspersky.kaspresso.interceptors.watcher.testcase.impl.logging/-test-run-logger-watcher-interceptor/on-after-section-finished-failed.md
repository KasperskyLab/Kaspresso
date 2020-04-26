[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [TestRunLoggerWatcherInterceptor](index.md) / [onAfterSectionFinishedFailed](./on-after-section-finished-failed.md)

# onAfterSectionFinishedFailed

`fun onAfterSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Logs the given [testInfo](on-after-section-finished-failed.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onAfterSectionFinishedFailed(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Throwable)/testInfo) on "after" section finishes with failure.

### Parameters

`testInfo` - the test info to log.

`throwable` - the error occurred to log.