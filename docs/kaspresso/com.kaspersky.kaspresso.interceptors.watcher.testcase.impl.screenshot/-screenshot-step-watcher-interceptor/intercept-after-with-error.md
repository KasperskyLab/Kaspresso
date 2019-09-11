[kaspresso](../../index.md) / [com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot](../index.md) / [ScreenshotStepWatcherInterceptor](index.md) / [interceptAfterWithError](./intercept-after-with-error.md)

# interceptAfterWithError

`fun interceptAfterWithError(stepInfo: `[`StepInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md)`, error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [StepWatcherInterceptor.interceptAfterWithError](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/intercept-after-with-error.md)

Takes a screenshot of the screen on which the step falied.

### Parameters

`stepInfo` - the step info to log.

`error` - the error occurred to use in screenshots name.