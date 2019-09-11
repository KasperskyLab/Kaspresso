[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging](../index.md) / [TestRunLoggerWatcherInterceptor](index.md) / [onTestFinished](./on-test-finished.md)

# onTestFinished

`fun onTestFinished(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, success: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [TestRunWatcherInterceptor.onTestFinished](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/on-test-finished.md)

Logs the given [testInfo](on-test-finished.md#com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor$onTestFinished(com.kaspersky.kaspresso.testcases.models.info.TestInfo, kotlin.Boolean)/testInfo) on whole test finishes.

### Parameters

`testInfo` - the test info to log.

`success` - the while test was finished successfully or not.