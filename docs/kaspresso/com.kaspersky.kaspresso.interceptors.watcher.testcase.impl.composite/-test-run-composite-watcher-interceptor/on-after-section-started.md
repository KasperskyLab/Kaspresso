[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md) / [TestRunCompositeWatcherInterceptor](index.md) / [onAfterSectionStarted](./on-after-section-started.md)

# onAfterSectionStarted

`fun onAfterSectionStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Called on "after" section starts, delegates the interception to [watcherInterceptors](#).

### Parameters

`testInfo` - the test info to pass to [watcherInterceptors](#).