[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite](../index.md) / [TestRunCompositeWatcherInterceptor](index.md) / [onBeforeSectionFinishedFailed](./on-before-section-finished-failed.md)

# onBeforeSectionFinishedFailed

`fun onBeforeSectionFinishedFailed(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, throwable: Throwable): Unit`

Called on "before" section finishes with failure, delegates the interception to [watcherInterceptors](#).

### Parameters

`testInfo` - the test info to pass to [watcherInterceptors](#).

`throwable` - the error occured to pass to  [watcherInterceptors](#).