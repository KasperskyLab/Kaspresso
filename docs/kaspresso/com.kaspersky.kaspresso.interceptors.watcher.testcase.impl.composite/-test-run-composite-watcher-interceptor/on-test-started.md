[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md) / [TestRunCompositeWatcherInterceptor](index.md) / [onTestStarted](./on-test-started.md)

# onTestStarted

`fun onTestStarted(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): Unit`

Called on the whole test starts, delegates the interception to [watcherInterceptors](#).

### Parameters

`testInfo` - the test info to pass to [watcherInterceptors](#).