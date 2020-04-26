[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [TestRunnerScreenshotWatcherInterceptor](index.md) / [onBeforeSectionFinishedFailed](./on-before-section-finished-failed.md)

# onBeforeSectionFinishedFailed

`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Takes a screenshot of the screen on which the "before" section failed.

### Parameters

`testInfo` - the test info to use in screenshots name.

`throwable` - the error occurred to use in screenshots name.